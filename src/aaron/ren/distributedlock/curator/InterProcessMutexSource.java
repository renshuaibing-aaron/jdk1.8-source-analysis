package aaron.ren.distributedlock.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.*;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.util.concurrent.MoreExecutors;
import org.apache.curator.utils.PathUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * InterProcessMutex基于Zookeeper实现了分布式的公平可重入互斥锁
 */
public class InterProcessMutexSource implements InterProcessLock, Revocable<InterProcessMutexSource> {
    private final LockInternals internals;
    private final String basePath;
    // 映射表
    // 记录线程与锁信息的映射关系
    private final ConcurrentMap<Thread, InterProcessMutexSource.LockData> threadData;
    private static final String LOCK_NAME = "lock-";

    public InterProcessMutexSource(CuratorFramework client, String path) {
        // Zookeeper利用path创建临时顺序节点，实现公平锁的核心
        this(client, path, new StandardLockInternalsDriver());
    }

    public InterProcessMutexSource(CuratorFramework client, String path, LockInternalsDriver driver) {
        // maxLeases=1，表示可以获得分布式锁的线程数量（跨JVM）为1，即为互斥锁
        this(client, path, "lock-", 1, driver);
    }
    // 无限等待
    @Override
    public void acquire() throws Exception {
        if (!this.internalLock(-1L, (TimeUnit)null)) {
            throw new IOException("Lost connection while trying to acquire lock: " + this.basePath);
        }
    }
    // 限时等待
    @Override
    public boolean acquire(long time, TimeUnit unit) throws Exception {
        return this.internalLock(time, unit);
    }
    @Override
    public boolean isAcquiredInThisProcess() {
        return this.threadData.size() > 0;
    }
    @Override
    public void release() throws Exception {
        Thread currentThread = Thread.currentThread();
        InterProcessMutexSource.LockData lockData = (InterProcessMutexSource.LockData)this.threadData.get(currentThread);
        if (lockData == null) {
            throw new IllegalMonitorStateException("You do not own the lock: " + this.basePath);
        } else {
            int newLockCount = lockData.lockCount.decrementAndGet();
            if (newLockCount <= 0) {
                if (newLockCount < 0) {
                    throw new IllegalMonitorStateException("Lock count has gone negative for lock: " + this.basePath);
                } else {
                    try {
                        this.internals.releaseLock(lockData.lockPath);
                    } finally {
                        this.threadData.remove(currentThread);
                    }

                }
            }
        }
    }

    public Collection<String> getParticipantNodes() throws Exception {
        return LockInternals.getParticipantNodes(this.internals.getClient(), this.basePath, this.internals.getLockName(), this.internals.getDriver());
    }
    @Override
    public void makeRevocable(RevocationListener<InterProcessMutexSource> listener) {
        this.makeRevocable(listener, MoreExecutors.directExecutor());
    }
    @Override
    public void makeRevocable(final RevocationListener<InterProcessMutexSource> listener, Executor executor) {
        this.internals.makeRevocable(new RevocationSpec(executor, new Runnable() {
            @Override
            public void run() {
                listener.revocationRequested(InterProcessMutexSource.this);
            }
        }));
    }

    // internals的类型为LockInternals，InterProcessMutex将分布式锁的申请和释放操作委托给internals执行
    InterProcessMutexSource(CuratorFramework client, String path, String lockName, int maxLeases, LockInternalsDriver driver) {
        this.threadData = Maps.newConcurrentMap();
        this.basePath = PathUtils.validatePath(path);
        this.internals = new LockInternals(client, driver, path, lockName, maxLeases);
    }

    public boolean isOwnedByCurrentThread() {
        InterProcessMutexSource.LockData lockData = (InterProcessMutexSource.LockData)this.threadData.get(Thread.currentThread());
        return lockData != null && lockData.lockCount.get() > 0;
    }

    protected byte[] getLockNodeBytes() {
        return null;
    }

    protected String getLockPath() {
        InterProcessMutexSource.LockData lockData = (InterProcessMutexSource.LockData)this.threadData.get(Thread.currentThread());
        return lockData != null ? lockData.lockPath : null;
    }

    private boolean internalLock(long time, TimeUnit unit) throws Exception {
        Thread currentThread = Thread.currentThread();
        InterProcessMutexSource.LockData lockData = (InterProcessMutexSource.LockData)this.threadData.get(currentThread);
        if (lockData != null) {
            // 实现可重入
            // 同一线程再次acquire，首先判断当前的映射表内（threadData）是否有该线程的锁信息，如果有则原子+1
            lockData.lockCount.incrementAndGet();
            return true;
        } else {
            // 映射表内没有对应的锁信息，尝试通过LockInternals获取锁
            String lockPath = this.internals.attemptLock(time, unit, this.getLockNodeBytes());
            if (lockPath != null) {
                // 成功获取锁，记录信息到映射表
                InterProcessMutexSource.LockData newLockData = new InterProcessMutexSource.LockData(currentThread, lockPath);
                this.threadData.put(currentThread, newLockData);
                return true;
            } else {
                return false;
            }
        }
    }
    // 锁信息
    // Zookeeper中一个临时顺序节点对应一个“锁”，但让锁生效激活需要排队（公平锁），下面会继续分析
    private static class LockData {
        final Thread owningThread;
        final String lockPath;
        final AtomicInteger lockCount; // 分布式锁重入次数

        private LockData(Thread owningThread, String lockPath) {
            this.lockCount = new AtomicInteger(1);
            this.owningThread = owningThread;
            this.lockPath = lockPath;
        }
    }
}
