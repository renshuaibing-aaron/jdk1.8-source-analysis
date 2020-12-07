/*
package aaron.ren.distributedlock.redission;

import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.internal.PlatformDependent;
import org.redisson.RedissonLockEntry;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.client.codec.LongCodec;
import org.redisson.client.protocol.RedisCommands;
import org.redisson.client.protocol.RedisStrictCommand;
import org.redisson.command.CommandAsyncExecutor;
import org.redisson.misc.RPromise;
import org.redisson.misc.RedissonPromise;
import org.redisson.pubsub.LockPubSub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;

*/
/**
 * redis 在单机部署下的分布式锁实现
 *//*

public class RedissonLockSource extends RedissonExpirableSource implements RLock {
    private static final Logger log = LoggerFactory.getLogger(RedissonLockSource.class);
    private static final ConcurrentMap<String, Timeout> expirationRenewalMap = PlatformDependent.newConcurrentHashMap();
    */
/**
     * 锁的时长
     *//*

    protected long internalLockLeaseTime;
    final UUID id;
    protected static final LockPubSub PUBSUB = new LockPubSub();
    final CommandAsyncExecutor commandExecutor;

    public RedissonLockSource(CommandAsyncExecutor commandExecutor, String name) {
        super(commandExecutor, name);
        this.commandExecutor = commandExecutor;
        this.id = commandExecutor.getConnectionManager().getId();
        this.internalLockLeaseTime = commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout();
    }

    protected String getEntryName() {
        return this.id + ":" + this.getName();
    }

    */
/**
     * 该分布式锁对应的 Channel 名。因为 RedissonLock 释放锁时，会通过该 Channel 来 Publish 一条消息，通知其它可能在阻塞等待这条消息的客户端
     *
     * @return
     *//*

    String getChannelName() {
        return prefixName("redisson_lock__channel", this.getName());
    }

    String getLockName(long threadId) {
        return this.id + ":" + threadId;
    }

    @Override
    public void lock() {
        try {
            this.lockInterruptibly();
        } catch (InterruptedException var2) {
            Thread.currentThread().interrupt();
        }

    }

    @Override
    public void lock(long leaseTime, TimeUnit unit) {
        try {
            this.lockInterruptibly(leaseTime, unit);
        } catch (InterruptedException var5) {
            Thread.currentThread().interrupt();
        }

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        this.lockInterruptibly(-1L, (TimeUnit) null);
    }

    @Override
    public void lockInterruptibly(long leaseTime, TimeUnit unit) throws InterruptedException {
        long threadId = Thread.currentThread().getId();
        Long ttl = this.tryAcquire(leaseTime, unit, threadId);
        if (ttl != null) {
            RFuture<RedissonLockEntry> future = this.subscribe(threadId);
            this.commandExecutor.syncSubscription(future);

            try {
                while (true) {
                    ttl = this.tryAcquire(leaseTime, unit, threadId);
                    if (ttl == null) {
                        return;
                    }

                    if (ttl >= 0L) {
                        this.getEntry(threadId).getLatch().tryAcquire(ttl, TimeUnit.MILLISECONDS);
                    } else {
                        this.getEntry(threadId).getLatch().acquire();
                    }
                }
            } finally {
                this.unsubscribe(future, threadId);
            }
        }
    }

    private Long tryAcquire(long leaseTime, TimeUnit unit, long threadId) {
        return (Long) this.get(this.tryAcquireAsync(leaseTime, unit, threadId));
    }

    private RFuture<Boolean> tryAcquireOnceAsync(long leaseTime, TimeUnit unit, final long threadId) {
        if (leaseTime != -1L) {
            return this.tryLockInnerAsync(leaseTime, unit, threadId, RedisCommands.EVAL_NULL_BOOLEAN);
        } else {
            RFuture<Boolean> ttlRemainingFuture = this.tryLockInnerAsync(this.commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout(), TimeUnit.MILLISECONDS, threadId, RedisCommands.EVAL_NULL_BOOLEAN);
            ttlRemainingFuture.addListener(new FutureListener<Boolean>() {
                @Override
                public void operationComplete(Future<Boolean> future) throws Exception {
                    if (future.isSuccess()) {
                        Boolean ttlRemaining = (Boolean) future.getNow();
                        if (ttlRemaining) {
                            RedissonLockSource.this.scheduleExpirationRenewal(threadId);
                        }

                    }
                }
            });
            return ttlRemainingFuture;
        }
    }

    private <T> RFuture<Long> tryAcquireAsync(long leaseTime, TimeUnit unit, final long threadId) {
        if (leaseTime != -1L) {
            return this.tryLockInnerAsync(leaseTime, unit, threadId, RedisCommands.EVAL_LONG);
        } else {
            RFuture<Long> ttlRemainingFuture = this.tryLockInnerAsync(this.commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout(), TimeUnit.MILLISECONDS, threadId, RedisCommands.EVAL_LONG);
            ttlRemainingFuture.addListener(new FutureListener<Long>() {
                @Override
                public void operationComplete(Future<Long> future) throws Exception {
                    if (future.isSuccess()) {
                        Long ttlRemaining = (Long) future.getNow();
                        if (ttlRemaining == null) {
                            RedissonLockSource.this.scheduleExpirationRenewal(threadId);
                        }

                    }
                }
            });
            return ttlRemainingFuture;
        }
    }

    @Override
    public boolean tryLock() {
        return (Boolean) this.get(this.tryLockAsync());
    }

    private void scheduleExpirationRenewal(final long threadId) {
        if (!expirationRenewalMap.containsKey(this.getEntryName())) {
            Timeout task = this.commandExecutor.getConnectionManager().newTimeout(new TimerTask() {
                @Override
                public void run(Timeout timeout) throws Exception {
                    RFuture<Boolean> future = RedissonLockSource.this.commandExecutor.evalWriteAsync(RedissonLockSource.this.getName(), LongCodec.INSTANCE, RedisCommands.EVAL_BOOLEAN,
                            "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " +// 情况一，如果持有锁，则重新设置过期时间为 ARGV[1] internalLockLeaseTime ，并返回 1 续租成功。
                                    "redis.call('pexpire', KEYS[1], ARGV[1]);" +
                                    " return 1; " +
                                    "end; " +
                                    "return 0;",// 情况二，未持有锁，返回 0 续租失败
                            Collections.singletonList(RedissonLockSource.this.getName()),
                            new Object[]{RedissonLockSource.this.internalLockLeaseTime, RedissonLockSource.this.getLockName(threadId)});
                    future.addListener(new FutureListener<Boolean>() {
                        @Override
                        public void operationComplete(Future<Boolean> future) throws Exception {
                            RedissonLockSource.expirationRenewalMap.remove(RedissonLockSource.this.getEntryName());
                            if (!future.isSuccess()) {
                                RedissonLockSource.log.error("Can't update lock " + RedissonLockSource.this.getName() + " expiration", future.cause());
                            } else {
                                if ((Boolean) future.getNow()) {
                                    RedissonLockSource.this.scheduleExpirationRenewal(threadId);
                                }

                            }
                        }
                    });
                }
            }, this.internalLockLeaseTime / 3L, TimeUnit.MILLISECONDS);
            if (expirationRenewalMap.putIfAbsent(this.getEntryName(), task) != null) {
                task.cancel();
            }

        }
    }

    void cancelExpirationRenewal() {
        Timeout task = (Timeout) expirationRenewalMap.remove(this.getEntryName());
        if (task != null) {
            task.cancel();
        }

    }

    */
/**
     * 实现加锁逻辑，并且支持可重入性
     *
     * @param leaseTime
     * @param unit
     * @param threadId
     * @param command
     * @param <T>
     * @return
     *//*

    <T> RFuture<T> tryLockInnerAsync(long leaseTime, TimeUnit unit, long threadId, RedisStrictCommand<T> command) {
        this.internalLockLeaseTime = unit.toMillis(leaseTime);
        return this.commandExecutor.evalWriteAsync(this.getName(),
                LongCodec.INSTANCE, command,
                "if (redis.call('exists', KEYS[1]) == 0) then" +  // 情况一，当前分布式锁被未被获得
                        " redis.call('hset', KEYS[1], ARGV[2], 1); " + // 写入分布锁被 ARGV[2] 获取到了，设置数量为 1 。
                        "redis.call('pexpire', KEYS[1], ARGV[1]);" +//设置分布式的过期时间为 ARGV[1]
                        " return nil; end; " +// 返回 null ，表示成功
                        "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1)" +// 情况二，如果当前分布锁已经被 ARGV[2] 持有
                        " then redis.call('hincrby', KEYS[1], ARGV[2], 1);" + // 写入持有计数字 + 1 。
                        " redis.call('pexpire', KEYS[1], ARGV[1]); " +//  设置分布式的过期时间为 ARGV[1]
                        "return nil; end;" +// 返回 null ，表示成功
                        " return redis.call('pttl', KEYS[1]);"// 情况三，获取不到分布式锁，则返回锁的过期时间。
                , Collections.singletonList(this.getName()),// KEYS[分布式锁名]
                new Object[]{this.internalLockLeaseTime, this.getLockName(threadId)}); // ARGV[锁超时时间，获得的锁名]
    }

    private void acquireFailed(long threadId) {
        this.get(this.acquireFailedAsync(threadId));
    }

    protected RFuture<Void> acquireFailedAsync(long threadId) {
        return RedissonPromise.newSucceededFuture(null);
    }

    @Override
    public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
        long time = unit.toMillis(waitTime);
        long current = System.currentTimeMillis();
        final long threadId = Thread.currentThread().getId();
        Long ttl = this.tryAcquire(leaseTime, unit, threadId);
        if (ttl == null) {
            return true;
        } else {
            time -= System.currentTimeMillis() - current;
            if (time <= 0L) {
                this.acquireFailed(threadId);
                return false;
            } else {
                current = System.currentTimeMillis();
                final RFuture<RedissonLockEntry> subscribeFuture = this.subscribe(threadId);
                if (!this.await(subscribeFuture, time, TimeUnit.MILLISECONDS)) {
                    if (!subscribeFuture.cancel(false)) {
                        subscribeFuture.addListener(new FutureListener<RedissonLockEntry>() {
                            @Override
                            public void operationComplete(Future<RedissonLockEntry> future) throws Exception {
                                if (subscribeFuture.isSuccess()) {
                                    RedissonLockSource.this.unsubscribe(subscribeFuture, threadId);
                                }

                            }
                        });
                    }

                    this.acquireFailed(threadId);
                    return false;
                } else {
                    try {
                        time -= System.currentTimeMillis() - current;
                        if (time <= 0L) {
                            this.acquireFailed(threadId);
                            boolean var20 = false;
                            return var20;
                        } else {
                            boolean var16;
                            do {
                                long currentTime = System.currentTimeMillis();
                                ttl = this.tryAcquire(leaseTime, unit, threadId);
                                if (ttl == null) {
                                    var16 = true;
                                    return var16;
                                }

                                time -= System.currentTimeMillis() - currentTime;
                                if (time <= 0L) {
                                    this.acquireFailed(threadId);
                                    var16 = false;
                                    return var16;
                                }

                                currentTime = System.currentTimeMillis();
                                if (ttl >= 0L && ttl < time) {
                                    this.getEntry(threadId).getLatch().tryAcquire(ttl, TimeUnit.MILLISECONDS);
                                } else {
                                    this.getEntry(threadId).getLatch().tryAcquire(time, TimeUnit.MILLISECONDS);
                                }

                                time -= System.currentTimeMillis() - currentTime;
                            } while (time > 0L);

                            this.acquireFailed(threadId);
                            var16 = false;
                            return var16;
                        }
                    } finally {
                        this.unsubscribe(subscribeFuture, threadId);
                    }
                }
            }
        }
    }

    protected RedissonLockEntry getEntry(long threadId) {
        return (RedissonLockEntry) PUBSUB.getEntry(this.getEntryName());
    }

    protected RFuture<RedissonLockEntry> subscribe(long threadId) {
        return PUBSUB.subscribe(this.getEntryName(), this.getChannelName(), this.commandExecutor.getConnectionManager().getSubscribeService());
    }

    protected void unsubscribe(RFuture<RedissonLockEntry> future, long threadId) {
        PUBSUB.unsubscribe(future.getNow(), this.getEntryName(), this.getChannelName(), this.commandExecutor.getConnectionManager().getSubscribeService());
    }

    @Override
    public boolean tryLock(long waitTime, TimeUnit unit) throws InterruptedException {
        return this.tryLock(waitTime, -1L, unit);
    }

    @Override
    public void unlock() {
        Boolean opStatus = (Boolean) this.get(this.unlockInnerAsync(Thread.currentThread().getId()));
        if (opStatus == null) {
            throw new IllegalMonitorStateException("attempt to unlock lock, not locked by current thread by node id: " + this.id + " thread-id: " + Thread.currentThread().getId());
        } else {
            if (opStatus) {
                this.cancelExpirationRenewal();
            }

        }
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forceUnlock() {
        this.get(this.forceUnlockAsync());
    }

    */
/**
     * 实现强制解锁逻辑
     *
     * @return
     *//*

    @Override
    public RFuture<Boolean> forceUnlockAsync() {
        this.cancelExpirationRenewal();
        return this.commandExecutor.evalWriteAsync(this.getName(), LongCodec.INSTANCE,
                RedisCommands.EVAL_BOOLEAN,
                "if (redis.call('del', KEYS[1]) == 1) then" + // 情况一，释放锁成功，则通过 Publish 发布释放锁的消息，并返回 1
                        " redis.call('publish', KEYS[2], ARGV[1]); " +
                        "return 1 else return 0 end", // 情况二，释放锁失败，因为不存在这个 KEY ，所以返回 0
                Arrays.asList(this.getName(), this.getChannelName()),
                new Object[]{LockPubSub.unlockMessage});
    }

    @Override
    public boolean isLocked() {
        return this.isExists();
    }

    @Override
    public boolean isHeldByThread(long l) {
        return false;
    }

    @Override
    public RFuture<Boolean> isExistsAsync() {
        return this.commandExecutor.writeAsync(this.getName(), this.codec, RedisCommands.EXISTS, new Object[]{this.getName()});
    }

    @Override
    public boolean isHeldByCurrentThread() {
        RFuture<Boolean> future = this.commandExecutor.writeAsync(this.getName(), LongCodec.INSTANCE, RedisCommands.HEXISTS, new Object[]{this.getName(), this.getLockName(Thread.currentThread().getId())});
        return (Boolean) this.get(future);
    }

    @Override
    public int getHoldCount() {
        RFuture<Long> future = this.commandExecutor.writeAsync(this.getName(), LongCodec.INSTANCE, RedisCommands.HGET, new Object[]{this.getName(), this.getLockName(Thread.currentThread().getId())});
        Long res = (Long) this.get(future);
        return res == null ? 0 : res.intValue();
    }

    @Override
    public RFuture<Boolean> deleteAsync() {
        return this.forceUnlockAsync();
    }

    @Override
    public RFuture<Void> unlockAsync() {
        long threadId = Thread.currentThread().getId();
        return this.unlockAsync(threadId);
    }

    protected RFuture<Boolean> unlockInnerAsync(long threadId) {
        return this.commandExecutor.evalWriteAsync(this.getName(), LongCodec.INSTANCE, RedisCommands.EVAL_BOOLEAN,
                "if (redis.call('exists', KEYS[1]) == 0) then redis.call('publish', KEYS[2], ARGV[1]); " +
                        "return 1;" +
                        " end;" +
                        "if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then return nil;" +
                        "end; " +  // 情况一，分布式锁未被 ARGV[3] 持有，则直接返回 null ，表示解锁失败。
                        "local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1); " + // 持有锁的数量减 1
                        "if (counter > 0) then redis.call('pexpire', KEYS[1], ARGV[2]);" + // 情况二，如果后还有剩余的持有锁数量，则返回 0 ，
                        // 表示解锁未完成 重新设置过期时间为 ARGV[2]
                        " return 0;" +
                        " else redis.call('del', KEYS[1]); " + // 情况三，不存在剩余的锁数量，则返回 1 ，表示解锁成功 删除对应的分布式锁对应的 KEYS[1]
                        "redis.call('publish', KEYS[2], ARGV[1]); return 1; " + // 发布解锁事件到 KEYS[2] ，通知气他可能要获取锁的线程
                        "end; " +
                        "return nil;",  // 不存在这个情况。
                Arrays.asList(this.getName(), this.getChannelName()), // KEYS[分布式锁名, 该分布式锁对应的 Channel 名]
                new Object[]{LockPubSub.unlockMessage, this.internalLockLeaseTime, this.getLockName(threadId)});// ARGV[解锁消息，锁超时时间，获得的锁名]
    }

    @Override
    public RFuture<Void> unlockAsync(final long threadId) {
        final RPromise<Void> result = new RedissonPromise();
        RFuture<Boolean> future = this.unlockInnerAsync(threadId);
        future.addListener(new FutureListener<Boolean>() {
            @Override
            public void operationComplete(Future<Boolean> future) throws Exception {
                if (!future.isSuccess()) {
                    result.tryFailure(future.cause());
                } else {
                    Boolean opStatus = (Boolean) future.getNow();
                    if (opStatus == null) {
                        IllegalMonitorStateException cause = new IllegalMonitorStateException("attempt to unlock lock, not locked by current thread by node id: " + RedissonLockSource.this.id + " thread-id: " + threadId);
                        result.tryFailure(cause);
                    } else {
                        if (opStatus) {
                            RedissonLockSource.this.cancelExpirationRenewal();
                        }

                        result.trySuccess(null);
                    }
                }
            }
        });
        return result;
    }

    @Override
    public RFuture<Void> lockAsync() {
        return this.lockAsync(-1L, (TimeUnit) null);
    }

    @Override
    public RFuture<Void> lockAsync(long leaseTime, TimeUnit unit) {
        long currentThreadId = Thread.currentThread().getId();
        return this.lockAsync(leaseTime, unit, currentThreadId);
    }

    @Override
    public RFuture<Void> lockAsync(long currentThreadId) {
        return this.lockAsync(-1L, (TimeUnit) null, currentThreadId);
    }

    @Override
    public RFuture<Void> lockAsync(final long leaseTime, final TimeUnit unit, final long currentThreadId) {
        final RPromise<Void> result = new RedissonPromise();
        RFuture<Long> ttlFuture = this.tryAcquireAsync(leaseTime, unit, currentThreadId);
        ttlFuture.addListener(new FutureListener<Long>() {
            @Override
            public void operationComplete(Future<Long> future) throws Exception {
                if (!future.isSuccess()) {
                    result.tryFailure(future.cause());
                } else {
                    Long ttl = (Long) future.getNow();
                    if (ttl == null) {
                        if (!result.trySuccess(null)) {
                            RedissonLockSource.this.unlockAsync(currentThreadId);
                        }

                    } else {
                        final RFuture<RedissonLockEntry> subscribeFuture = RedissonLockSource.this.subscribe(currentThreadId);
                        subscribeFuture.addListener(new FutureListener<RedissonLockEntry>() {
                            @Override
                            public void operationComplete(Future<RedissonLockEntry> future) throws Exception {
                                if (!future.isSuccess()) {
                                    result.tryFailure(future.cause());
                                } else {
                                    RedissonLockSource.this.lockAsync(leaseTime, unit, subscribeFuture, result, currentThreadId);
                                }
                            }
                        });
                    }
                }
            }
        });
        return result;
    }

    private void lockAsync(final long leaseTime, final TimeUnit unit, final RFuture<RedissonLockEntry> subscribeFuture, final RPromise<Void> result, final long currentThreadId) {
        RFuture<Long> ttlFuture = this.tryAcquireAsync(leaseTime, unit, currentThreadId);
        ttlFuture.addListener(new FutureListener<Long>() {
            @Override
            public void operationComplete(Future<Long> future) throws Exception {
                if (!future.isSuccess()) {
                    RedissonLockSource.this.unsubscribe(subscribeFuture, currentThreadId);
                    result.tryFailure(future.cause());
                } else {
                    Long ttl = (Long) future.getNow();
                    if (ttl == null) {
                        RedissonLockSource.this.unsubscribe(subscribeFuture, currentThreadId);
                        if (!result.trySuccess(null)) {
                            RedissonLockSource.this.unlockAsync(currentThreadId);
                        }

                    } else {
                        final RedissonLockEntry entry = RedissonLockSource.this.getEntry(currentThreadId);
                        synchronized (entry) {
                            if (entry.getLatch().tryAcquire()) {
                                RedissonLockSource.this.lockAsync(leaseTime, unit, subscribeFuture, result, currentThreadId);
                            } else {
                                final AtomicReference<Timeout> futureRef = new AtomicReference();
                                final Runnable listener = new Runnable() {
                                    @Override
                                    public void run() {
                                        if (futureRef.get() != null) {
                                            ((Timeout) futureRef.get()).cancel();
                                        }

                                        RedissonLockSource.this.lockAsync(leaseTime, unit, subscribeFuture, result, currentThreadId);
                                    }
                                };
                                entry.addListener(listener);
                                if (ttl >= 0L) {
                                    Timeout scheduledFuture = RedissonLockSource.this.commandExecutor.getConnectionManager().newTimeout(new TimerTask() {
                                        @Override
                                        public void run(Timeout timeout) throws Exception {
                                            synchronized (entry) {
                                                if (entry.removeListener(listener)) {
                                                    RedissonLockSource.this.lockAsync(leaseTime, unit, subscribeFuture, result, currentThreadId);
                                                }

                                            }
                                        }
                                    }, ttl, TimeUnit.MILLISECONDS);
                                    futureRef.set(scheduledFuture);
                                }
                            }

                        }
                    }
                }
            }
        });
    }

    @Override
    public RFuture<Boolean> tryLockAsync() {
        return this.tryLockAsync(Thread.currentThread().getId());
    }

    @Override
    public RFuture<Boolean> tryLockAsync(long threadId) {
        return this.tryAcquireOnceAsync(-1L, (TimeUnit) null, threadId);
    }

    @Override
    public RFuture<Boolean> tryLockAsync(long waitTime, TimeUnit unit) {
        return this.tryLockAsync(waitTime, -1L, unit);
    }

    @Override
    public RFuture<Boolean> tryLockAsync(long waitTime, long leaseTime, TimeUnit unit) {
        long currentThreadId = Thread.currentThread().getId();
        return this.tryLockAsync(waitTime, leaseTime, unit, currentThreadId);
    }

    @Override
    public RFuture<Boolean> tryLockAsync(long waitTime, final long leaseTime, final TimeUnit unit, final long currentThreadId) {
        final RPromise<Boolean> result = new RedissonPromise();
        final AtomicLong time = new AtomicLong(unit.toMillis(waitTime));
        final long currentTime = System.currentTimeMillis();
        RFuture<Long> ttlFuture = this.tryAcquireAsync(leaseTime, unit, currentThreadId);
        ttlFuture.addListener(new FutureListener<Long>() {
            @Override
            public void operationComplete(Future<Long> future) throws Exception {
                if (!future.isSuccess()) {
                    result.tryFailure(future.cause());
                } else {
                    Long ttl = (Long) future.getNow();
                    if (ttl == null) {
                        if (!result.trySuccess(true)) {
                            RedissonLockSource.this.unlockAsync(currentThreadId);
                        }

                    } else {
                        long elapsed = System.currentTimeMillis() - currentTime;
                        time.addAndGet(-elapsed);
                        if (time.get() <= 0L) {
                            RedissonLockSource.this.trySuccessFalse(currentThreadId, result);
                        } else {
                            final long current = System.currentTimeMillis();
                            final AtomicReference<Timeout> futureRef = new AtomicReference();
                            final RFuture<RedissonLockEntry> subscribeFuture = RedissonLockSource.this.subscribe(currentThreadId);
                            subscribeFuture.addListener(new FutureListener<RedissonLockEntry>() {
                                @Override
                                public void operationComplete(Future<RedissonLockEntry> future) throws Exception {
                                    if (!future.isSuccess()) {
                                        result.tryFailure(future.cause());
                                    } else {
                                        if (futureRef.get() != null) {
                                            ((Timeout) futureRef.get()).cancel();
                                        }

                                        long elapsed = System.currentTimeMillis() - current;
                                        time.addAndGet(-elapsed);
                                        RedissonLockSource.this.tryLockAsync(time, leaseTime, unit, subscribeFuture, result, currentThreadId);
                                    }
                                }
                            });
                            if (!subscribeFuture.isDone()) {
                                Timeout scheduledFuture = RedissonLockSource.this.commandExecutor.getConnectionManager().newTimeout(new TimerTask() {
                                    @Override
                                    public void run(Timeout timeout) throws Exception {
                                        if (!subscribeFuture.isDone()) {
                                            subscribeFuture.cancel(false);
                                            RedissonLockSource.this.trySuccessFalse(currentThreadId, result);
                                        }

                                    }
                                }, time.get(), TimeUnit.MILLISECONDS);
                                futureRef.set(scheduledFuture);
                            }

                        }
                    }
                }
            }
        });
        return result;
    }

    @Override
    public RFuture<Integer> getHoldCountAsync() {
        return null;
    }

    @Override
    public RFuture<Boolean> isLockedAsync() {
        return null;
    }

    private void trySuccessFalse(long currentThreadId, final RPromise<Boolean> result) {
        this.acquireFailedAsync(currentThreadId).addListener(new FutureListener<Void>() {
            @Override
            public void operationComplete(Future<Void> future) throws Exception {
                if (future.isSuccess()) {
                    result.trySuccess(false);
                } else {
                    result.tryFailure(future.cause());
                }

            }
        });
    }

    private void tryLockAsync(final AtomicLong time, final long leaseTime, final TimeUnit unit, final RFuture<RedissonLockEntry> subscribeFuture, final RPromise<Boolean> result, final long currentThreadId) {
        if (result.isDone()) {
            this.unsubscribe(subscribeFuture, currentThreadId);
        } else if (time.get() <= 0L) {
            this.unsubscribe(subscribeFuture, currentThreadId);
            this.trySuccessFalse(currentThreadId, result);
        } else {
            final long current = System.currentTimeMillis();
            RFuture<Long> ttlFuture = this.tryAcquireAsync(leaseTime, unit, currentThreadId);
            ttlFuture.addListener(new FutureListener<Long>() {
                @Override
                public void operationComplete(Future<Long> future) throws Exception {
                    if (!future.isSuccess()) {
                        RedissonLockSource.this.unsubscribe(subscribeFuture, currentThreadId);
                        result.tryFailure(future.cause());
                    } else {
                        Long ttl = (Long) future.getNow();
                        if (ttl == null) {
                            RedissonLockSource.this.unsubscribe(subscribeFuture, currentThreadId);
                            if (!result.trySuccess(true)) {
                                RedissonLockSource.this.unlockAsync(currentThreadId);
                            }

                        } else {
                            long elapsed = System.currentTimeMillis() - current;
                            time.addAndGet(-elapsed);
                            if (time.get() <= 0L) {
                                RedissonLockSource.this.unsubscribe(subscribeFuture, currentThreadId);
                                RedissonLockSource.this.trySuccessFalse(currentThreadId, result);
                            } else {
                                final long currentx = System.currentTimeMillis();
                                final RedissonLockEntry entry = RedissonLockSource.this.getEntry(currentThreadId);
                                synchronized (entry) {
                                    if (entry.getLatch().tryAcquire()) {
                                        RedissonLockSource.this.tryLockAsync(time, leaseTime, unit, subscribeFuture, result, currentThreadId);
                                    } else {
                                        final AtomicBoolean executed = new AtomicBoolean();
                                        final AtomicReference<Timeout> futureRef = new AtomicReference();
                                        final Runnable listener = new Runnable() {
                                            @Override
                                            public void run() {
                                                executed.set(true);
                                                if (futureRef.get() != null) {
                                                    ((Timeout) futureRef.get()).cancel();
                                                }

                                                long elapsed = System.currentTimeMillis() - currentx;
                                                time.addAndGet(-elapsed);
                                                RedissonLockSource.this.tryLockAsync(time, leaseTime, unit, subscribeFuture, result, currentThreadId);
                                            }
                                        };
                                        entry.addListener(listener);
                                        long t = time.get();
                                        if (ttl >= 0L && ttl < time.get()) {
                                            t = ttl;
                                        }

                                        if (!executed.get()) {
                                            Timeout scheduledFuture = RedissonLockSource.this.commandExecutor.getConnectionManager().newTimeout(new TimerTask() {
                                                @Override
                                                public void run(Timeout timeout) throws Exception {
                                                    synchronized (entry) {
                                                        if (entry.removeListener(listener)) {
                                                            long elapsed = System.currentTimeMillis() - currentx;
                                                            time.addAndGet(-elapsed);
                                                            RedissonLockSource.this.tryLockAsync(time, leaseTime, unit, subscribeFuture, result, currentThreadId);
                                                        }

                                                    }
                                                }
                                            }, t, TimeUnit.MILLISECONDS);
                                            futureRef.set(scheduledFuture);
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            });
        }
    }
}
*/
