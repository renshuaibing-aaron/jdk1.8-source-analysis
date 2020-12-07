package org.apache.curator.framework.recipes.locks;

import com.google.common.base.Function;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.RetryLoop;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.ChildrenDeletable;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Iterables;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException.BadVersionException;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.apache.zookeeper.KeeperException.NotEmptyException;
import org.apache.zookeeper.Watcher.Event.EventType;

public class LockInternals {
    private final WatcherRemoveCuratorFramework client;
    private final String path;
    private final String basePath;
    private final LockInternalsDriver driver;
    private final String lockName;
    private final AtomicReference<RevocationSpec> revocable = new AtomicReference((Object)null);
    private final CuratorWatcher revocableWatcher = new CuratorWatcher() {
        @Override
        public void process(WatchedEvent event) throws Exception {
            if (event.getType() == EventType.NodeDataChanged) {
                LockInternals.this.checkRevocableWatcher(event.getPath());
            }

        }
    };
    private final Watcher watcher = new Watcher() {
        @Override
        public void process(WatchedEvent event) {
            LockInternals.this.notifyFromWatcher();
        }
    };
    private volatile int maxLeases;
    static final byte[] REVOKE_MESSAGE = "__REVOKE__".getBytes();

    public void clean() throws Exception {
        try {
            this.client.delete().forPath(this.basePath);
        } catch (BadVersionException var2) {
        } catch (NotEmptyException var3) {
        }

    }

    public LockInternals(CuratorFramework client, LockInternalsDriver driver, String path, String lockName, int maxLeases) {
        this.driver = driver;
        this.lockName = lockName;
        this.maxLeases = maxLeases;
        this.client = client.newWatcherRemoveCuratorFramework();
        this.basePath = PathUtils.validatePath(path);
        this.path = ZKPaths.makePath(path, lockName);
    }

    synchronized void setMaxLeases(int maxLeases) {
        this.maxLeases = maxLeases;
        this.notifyAll();
    }

    public void makeRevocable(RevocationSpec entry) {
        this.revocable.set(entry);
    }

    final  public void releaseLock(String lockPath) throws Exception {
        this.client.removeWatchers();
        this.revocable.set(null);
        this.deleteOurPath(lockPath);
    }

    public CuratorFramework getClient() {
        return this.client;
    }

    public static Collection<String> getParticipantNodes(CuratorFramework client, final String basePath, String lockName, LockInternalsSorter sorter) throws Exception {
        List<String> names = getSortedChildren(client, basePath, lockName, sorter);
        Iterable<String> transformed = Iterables.transform(names, new Function<String, String>() {
            @Override
            public String apply(String name) {
                return ZKPaths.makePath(basePath, name);
            }
        });
        return ImmutableList.copyOf(transformed);
    }

    public static List<String> getSortedChildren(CuratorFramework client, String basePath, final String lockName, final LockInternalsSorter sorter) throws Exception {
        try {
            List<String> children = (List)client.getChildren().forPath(basePath);
            List<String> sortedList = Lists.newArrayList(children);
            Collections.sort(sortedList, new Comparator<String>() {
                public int compare(String lhs, String rhs) {
                    return sorter.fixForSorting(lhs, lockName).compareTo(sorter.fixForSorting(rhs, lockName));
                }
            });
            return sortedList;
        } catch (NoNodeException var6) {
            return Collections.emptyList();
        }
    }

    public static List<String> getSortedChildren(final String lockName, final LockInternalsSorter sorter, List<String> children) {
        List<String> sortedList = Lists.newArrayList(children);
        Collections.sort(sortedList, new Comparator<String>() {
            public int compare(String lhs, String rhs) {
                return sorter.fixForSorting(lhs, lockName).compareTo(sorter.fixForSorting(rhs, lockName));
            }
        });
        return sortedList;
    }

    List<String> getSortedChildren() throws Exception {
        return getSortedChildren(this.client, this.basePath, this.lockName, this.driver);
    }

    public String getLockName() {
        return this.lockName;
    }

    public LockInternalsDriver getDriver() {
        return this.driver;
    }
    // 尝试获取锁，并返回锁对应的Zookeeper临时顺序节点的路径
    public String attemptLock(long time, TimeUnit unit, byte[] lockNodeBytes) throws Exception {
        long startMillis = System.currentTimeMillis();
        // 无限等待时，millisToWait为null
        Long millisToWait = unit != null ? unit.toMillis(time) : null;
        // 创建ZNode节点时的数据内容，无关紧要，这里为null，采用默认值（IP地址）
        byte[] localLockNodeBytes = this.revocable.get() != null ? new byte[0] : lockNodeBytes;
        // 当前已经重试次数，与CuratorFramework的重试策略有关
        int retryCount = 0;
        // 在Zookeeper中创建的临时顺序节点的路径，相当于一把待激活的分布式锁
        // 激活条件：同级目录子节点，名称排序最小（排队，公平锁），后续继续分析
        String ourPath = null;
        // 是否已经持有分布式锁
        boolean hasTheLock = false;
        // 是否已经完成尝试获取分布式锁的操作
        boolean isDone = false;

        while(!isDone) {
            isDone = true;

            try {
                // 从InterProcessMutex的构造函数可知实际driver为StandardLockInternalsDriver的实例
                // 在Zookeeper中创建临时顺序节点
                ourPath = this.driver.createsTheLock(this.client, this.path, localLockNodeBytes);
                // 循环等待来激活分布式锁，实现锁的公平性，后续继续分析
                hasTheLock = this.internalLockLoop(startMillis, millisToWait, ourPath);
            } catch (NoNodeException var14) {
                // 容错处理，不影响主逻辑的理解，可跳过
                // 因为会话过期等原因，StandardLockInternalsDriver因为无法找到创建的临时顺序节点而抛出异常
                if (!this.client.getZookeeperClient().getRetryPolicy().allowRetry(retryCount++, System.currentTimeMillis() - startMillis, RetryLoop.getDefaultRetrySleeper())) {
                    // 不满足重试策略则继续抛出NoNodeException
                    throw var14;
                }
                // 满足重试策略尝试重新获取锁
                isDone = false;
            }
        }
        // 成功获得分布式锁，返回临时顺序节点的路径，上层将其封装成锁信息记录在映射表，方便锁重入
        // 获取分布式锁失败，返回null
        return hasTheLock ? ourPath : null;
    }

    private void checkRevocableWatcher(String path) throws Exception {
        RevocationSpec entry = (RevocationSpec)this.revocable.get();
        if (entry != null) {
            try {
                byte[] bytes = (byte[])((BackgroundPathable)this.client.getData().usingWatcher(this.revocableWatcher)).forPath(path);
                if (Arrays.equals(bytes, REVOKE_MESSAGE)) {
                    entry.getExecutor().execute(entry.getRunnable());
                }
            } catch (NoNodeException var4) {
            }
        }

    }
    // 循环等待来激活分布式锁，实现锁的公平性
    private boolean internalLockLoop(long startMillis, Long millisToWait, String ourPath) throws Exception {
        // 是否已经持有分布式锁
        boolean haveTheLock = false;
        // 是否需要删除子节点
        boolean doDelete = false;

        try {
            if (this.revocable.get() != null) {

                ((BackgroundPathable)this.client.getData().usingWatcher(this.revocableWatcher)).forPath(ourPath);
            }

            while(this.client.getState() == CuratorFrameworkState.STARTED && !haveTheLock) {
                // 获取排序后的子节点列表
                List<String> children = this.getSortedChildren();
                // 获取前面自己创建的临时顺序子节点的名称
                String sequenceNodeName = ourPath.substring(this.basePath.length() + 1);
                // 实现锁的公平性的核心逻辑，看下面的分析
                PredicateResults predicateResults = this.driver.getsTheLock(this.client, children, sequenceNodeName, this.maxLeases);
                if (predicateResults.getsTheLock()) {
                    // 获得了锁，中断循环，继续返回上层
                    haveTheLock = true;
                } else {
                    // 没有获得到锁，监听上一临时顺序节点
                    String previousSequencePath = this.basePath + "/" + predicateResults.getPathToWatch();
                    synchronized(this) {
                        try {
                            // exists()会导致导致资源泄漏，因此exists()可以监听不存在的ZNode，因此采用getData()
                            // 上一临时顺序节点如果被删除，会唤醒当前线程继续竞争锁，正常情况下能直接获得锁
                            ((BackgroundPathable)this.client.getData().usingWatcher(this.watcher)).forPath(previousSequencePath);
                            if (millisToWait == null) {
                                this.wait();// 等待被唤醒，无限等待
                            } else {
                                millisToWait = millisToWait - (System.currentTimeMillis() - startMillis);
                                startMillis = System.currentTimeMillis();
                                if (millisToWait > 0L) {
                                    this.wait(millisToWait);// 等待被唤醒，限时等待
                                } else {
                                    doDelete = true;// 获取锁超时，标记删除之前创建的临时顺序节点
                                    break;
                                }
                            }
                        } catch (NoNodeException var19) {
                            // 容错处理，逻辑稍微有点绕，可跳过，不影响主逻辑的理解
                            // client.getData()可能调用时抛出NoNodeException，原因可能是锁被释放或会话过期（连接丢失）等
                            // 这里并没有做任何处理，因为外层是while循环，再次执行driver.getsTheLock时会调用validateOurIndex
                            // 此时会抛出NoNodeException，从而进入下面的catch和finally逻辑，重新抛出上层尝试重试获
                        }
                    }
                }
            }
        } catch (Exception var21) {
            // 标记删除，在finally删除之前创建的临时顺序节点（后台不断尝试）
            ThreadUtils.checkInterrupted(var21);
            // 重新抛出，尝试重新获取锁
            doDelete = true;
            throw var21;
        } finally {
            if (doDelete) {
                this.deleteOurPath(ourPath);
            }

        }

        return haveTheLock;
    }

    private void deleteOurPath(String ourPath) throws Exception {
        try {
            ((ChildrenDeletable)this.client.delete().guaranteed()).forPath(ourPath);
        } catch (NoNodeException var3) {
        }

    }

    private synchronized void notifyFromWatcher() {
        this.notifyAll();
    }
}
