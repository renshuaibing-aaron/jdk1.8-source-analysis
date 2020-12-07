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
 * InterProcessMutex����Zookeeperʵ���˷ֲ�ʽ�Ĺ�ƽ�����뻥����
 */
public class InterProcessMutexSource implements InterProcessLock, Revocable<InterProcessMutexSource> {
    private final LockInternals internals;
    private final String basePath;
    // ӳ���
    // ��¼�߳�������Ϣ��ӳ���ϵ
    private final ConcurrentMap<Thread, InterProcessMutexSource.LockData> threadData;
    private static final String LOCK_NAME = "lock-";

    public InterProcessMutexSource(CuratorFramework client, String path) {
        // Zookeeper����path������ʱ˳��ڵ㣬ʵ�ֹ�ƽ���ĺ���
        this(client, path, new StandardLockInternalsDriver());
    }

    public InterProcessMutexSource(CuratorFramework client, String path, LockInternalsDriver driver) {
        // maxLeases=1����ʾ���Ի�÷ֲ�ʽ�����߳���������JVM��Ϊ1����Ϊ������
        this(client, path, "lock-", 1, driver);
    }
    // ���޵ȴ�
    @Override
    public void acquire() throws Exception {
        if (!this.internalLock(-1L, (TimeUnit)null)) {
            throw new IOException("Lost connection while trying to acquire lock: " + this.basePath);
        }
    }
    // ��ʱ�ȴ�
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

    // internals������ΪLockInternals��InterProcessMutex���ֲ�ʽ����������ͷŲ���ί�и�internalsִ��
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
            // ʵ�ֿ�����
            // ͬһ�߳��ٴ�acquire�������жϵ�ǰ��ӳ����ڣ�threadData���Ƿ��и��̵߳�����Ϣ���������ԭ��+1
            lockData.lockCount.incrementAndGet();
            return true;
        } else {
            // ӳ�����û�ж�Ӧ������Ϣ������ͨ��LockInternals��ȡ��
            String lockPath = this.internals.attemptLock(time, unit, this.getLockNodeBytes());
            if (lockPath != null) {
                // �ɹ���ȡ������¼��Ϣ��ӳ���
                InterProcessMutexSource.LockData newLockData = new InterProcessMutexSource.LockData(currentThread, lockPath);
                this.threadData.put(currentThread, newLockData);
                return true;
            } else {
                return false;
            }
        }
    }
    // ����Ϣ
    // Zookeeper��һ����ʱ˳��ڵ��Ӧһ������������������Ч������Ҫ�Ŷӣ���ƽ������������������
    private static class LockData {
        final Thread owningThread;
        final String lockPath;
        final AtomicInteger lockCount; // �ֲ�ʽ���������

        private LockData(Thread owningThread, String lockPath) {
            this.lockCount = new AtomicInteger(1);
            this.owningThread = owningThread;
            this.lockPath = lockPath;
        }
    }
}
