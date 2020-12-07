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
    // ���Ի�ȡ��������������Ӧ��Zookeeper��ʱ˳��ڵ��·��
    public String attemptLock(long time, TimeUnit unit, byte[] lockNodeBytes) throws Exception {
        long startMillis = System.currentTimeMillis();
        // ���޵ȴ�ʱ��millisToWaitΪnull
        Long millisToWait = unit != null ? unit.toMillis(time) : null;
        // ����ZNode�ڵ�ʱ���������ݣ��޹ؽ�Ҫ������Ϊnull������Ĭ��ֵ��IP��ַ��
        byte[] localLockNodeBytes = this.revocable.get() != null ? new byte[0] : lockNodeBytes;
        // ��ǰ�Ѿ����Դ�������CuratorFramework�����Բ����й�
        int retryCount = 0;
        // ��Zookeeper�д�������ʱ˳��ڵ��·�����൱��һ�Ѵ�����ķֲ�ʽ��
        // ����������ͬ��Ŀ¼�ӽڵ㣬����������С���Ŷӣ���ƽ������������������
        String ourPath = null;
        // �Ƿ��Ѿ����зֲ�ʽ��
        boolean hasTheLock = false;
        // �Ƿ��Ѿ���ɳ��Ի�ȡ�ֲ�ʽ���Ĳ���
        boolean isDone = false;

        while(!isDone) {
            isDone = true;

            try {
                // ��InterProcessMutex�Ĺ��캯����֪ʵ��driverΪStandardLockInternalsDriver��ʵ��
                // ��Zookeeper�д�����ʱ˳��ڵ�
                ourPath = this.driver.createsTheLock(this.client, this.path, localLockNodeBytes);
                // ѭ���ȴ�������ֲ�ʽ����ʵ�����Ĺ�ƽ�ԣ�������������
                hasTheLock = this.internalLockLoop(startMillis, millisToWait, ourPath);
            } catch (NoNodeException var14) {
                // �ݴ�����Ӱ�����߼�����⣬������
                // ��Ϊ�Ự���ڵ�ԭ��StandardLockInternalsDriver��Ϊ�޷��ҵ���������ʱ˳��ڵ���׳��쳣
                if (!this.client.getZookeeperClient().getRetryPolicy().allowRetry(retryCount++, System.currentTimeMillis() - startMillis, RetryLoop.getDefaultRetrySleeper())) {
                    // ���������Բ���������׳�NoNodeException
                    throw var14;
                }
                // �������Բ��Գ������»�ȡ��
                isDone = false;
            }
        }
        // �ɹ���÷ֲ�ʽ����������ʱ˳��ڵ��·�����ϲ㽫���װ������Ϣ��¼��ӳ�������������
        // ��ȡ�ֲ�ʽ��ʧ�ܣ�����null
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
    // ѭ���ȴ�������ֲ�ʽ����ʵ�����Ĺ�ƽ��
    private boolean internalLockLoop(long startMillis, Long millisToWait, String ourPath) throws Exception {
        // �Ƿ��Ѿ����зֲ�ʽ��
        boolean haveTheLock = false;
        // �Ƿ���Ҫɾ���ӽڵ�
        boolean doDelete = false;

        try {
            if (this.revocable.get() != null) {

                ((BackgroundPathable)this.client.getData().usingWatcher(this.revocableWatcher)).forPath(ourPath);
            }

            while(this.client.getState() == CuratorFrameworkState.STARTED && !haveTheLock) {
                // ��ȡ�������ӽڵ��б�
                List<String> children = this.getSortedChildren();
                // ��ȡǰ���Լ���������ʱ˳���ӽڵ������
                String sequenceNodeName = ourPath.substring(this.basePath.length() + 1);
                // ʵ�����Ĺ�ƽ�Եĺ����߼���������ķ���
                PredicateResults predicateResults = this.driver.getsTheLock(this.client, children, sequenceNodeName, this.maxLeases);
                if (predicateResults.getsTheLock()) {
                    // ����������ж�ѭ�������������ϲ�
                    haveTheLock = true;
                } else {
                    // û�л�õ�����������һ��ʱ˳��ڵ�
                    String previousSequencePath = this.basePath + "/" + predicateResults.getPathToWatch();
                    synchronized(this) {
                        try {
                            // exists()�ᵼ�µ�����Դй©�����exists()���Լ��������ڵ�ZNode����˲���getData()
                            // ��һ��ʱ˳��ڵ������ɾ�����ỽ�ѵ�ǰ�̼߳����������������������ֱ�ӻ����
                            ((BackgroundPathable)this.client.getData().usingWatcher(this.watcher)).forPath(previousSequencePath);
                            if (millisToWait == null) {
                                this.wait();// �ȴ������ѣ����޵ȴ�
                            } else {
                                millisToWait = millisToWait - (System.currentTimeMillis() - startMillis);
                                startMillis = System.currentTimeMillis();
                                if (millisToWait > 0L) {
                                    this.wait(millisToWait);// �ȴ������ѣ���ʱ�ȴ�
                                } else {
                                    doDelete = true;// ��ȡ����ʱ�����ɾ��֮ǰ��������ʱ˳��ڵ�
                                    break;
                                }
                            }
                        } catch (NoNodeException var19) {
                            // �ݴ����߼���΢�е��ƣ�����������Ӱ�����߼������
                            // client.getData()���ܵ���ʱ�׳�NoNodeException��ԭ������������ͷŻ�Ự���ڣ����Ӷ�ʧ����
                            // ���ﲢû�����κδ�����Ϊ�����whileѭ�����ٴ�ִ��driver.getsTheLockʱ�����validateOurIndex
                            // ��ʱ���׳�NoNodeException���Ӷ����������catch��finally�߼��������׳��ϲ㳢�����Ի�
                        }
                    }
                }
            }
        } catch (Exception var21) {
            // ���ɾ������finallyɾ��֮ǰ��������ʱ˳��ڵ㣨��̨���ϳ��ԣ�
            ThreadUtils.checkInterrupted(var21);
            // �����׳����������»�ȡ��
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
