package aaron.ren.distributedlock.redission;

import org.redisson.RedissonObject;
import org.redisson.api.RExpirable;
import org.redisson.api.RFuture;
import org.redisson.client.codec.Codec;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.RedisCommands;
import org.redisson.command.CommandAsyncExecutor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract  class RedissonExpirableSource extends RedissonObject implements RExpirable {
    RedissonExpirableSource(CommandAsyncExecutor connectionManager, String name) {
        super(connectionManager, name);
    }

    RedissonExpirableSource(Codec codec, CommandAsyncExecutor connectionManager, String name) {
        super(codec, connectionManager, name);
    }
    @Override
    public boolean expire(long timeToLive, TimeUnit timeUnit) {
        return (Boolean)this.commandExecutor.get(this.expireAsync(timeToLive, timeUnit));
    }
    @Override
    public RFuture<Boolean> expireAsync(long timeToLive, TimeUnit timeUnit) {
        return this.commandExecutor.writeAsync(this.getName(), StringCodec.INSTANCE, RedisCommands.PEXPIRE, new Object[]{this.getName(), timeUnit.toMillis(timeToLive)});
    }
    @Override
    public boolean expireAt(long timestamp) {
        return (Boolean)this.commandExecutor.get(this.expireAtAsync(timestamp));
    }
    @Override
    public RFuture<Boolean> expireAtAsync(long timestamp) {
        return this.commandExecutor.writeAsync(this.getName(), StringCodec.INSTANCE, RedisCommands.PEXPIREAT, new Object[]{this.getName(), timestamp});
    }
    @Override
    public boolean expireAt(Date timestamp) {
        return this.expireAt(timestamp.getTime());
    }
    @Override
    public RFuture<Boolean> expireAtAsync(Date timestamp) {
        return this.expireAtAsync(timestamp.getTime());
    }
    @Override
    public boolean clearExpire() {
        return (Boolean)this.commandExecutor.get(this.clearExpireAsync());
    }
    @Override
    public RFuture<Boolean> clearExpireAsync() {
        return this.commandExecutor.writeAsync(this.getName(), StringCodec.INSTANCE, RedisCommands.PERSIST, new Object[]{this.getName()});
    }
    @Override
    public long remainTimeToLive() {
        return (Long)this.commandExecutor.get(this.remainTimeToLiveAsync());
    }
    @Override
    public RFuture<Long> remainTimeToLiveAsync() {
        return this.commandExecutor.readAsync(this.getName(), StringCodec.INSTANCE, RedisCommands.PTTL, new Object[]{this.getName()});
    }
}
