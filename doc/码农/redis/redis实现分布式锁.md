利用redis实现分布式锁有多种方案
1.采用Redission 框架实现 首先说明这个有个缺陷在哪里？

redis的原子脚本lua利用setna来争抢锁，然后再用expire给锁加一个过期时间防止锁忘记了释放 这个有个问题可能会导致这中间宕机 导致锁无法释放 
所以这里用到redis的事务 

在你的redis架构采用集群或者主从复制实现时  采用Redission 对某一个redis节点进行加锁(写一个缓存数据 key是你的设置的锁的名称 value值是客户端ID和重入的次数)，然后加上
过期时间 一旦在主节点复制到从节点时 集群出现宕机会导致 数据的不一致 然后出现的各种问题



思考：为什么ZK不会出现这个问题？


2.项目中是怎么使用分布式锁的？
官方推荐采用Redlock算法，即使用string类型，加锁的时候给的一个具体的key，然后设置一个随机的值；
取消锁的时候用使用lua脚本来先执行获取比较，然后再删除key。
SET resource_name my_random_value NX PX  30000
if redis.call( "get" ,KEYS[ 1 ]) == ARGV[ 1 ] then
return redis.call( "del" ,KEYS[ 1 ])
else
return  0
end

