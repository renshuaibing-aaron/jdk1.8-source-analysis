1.每个redis单机服务器启动后 
struct redisServer {
   // 一个数组保存服务器中的所有数据库 
    redisDb *db;
    int dbnum ; //服务器中数据库的数量
}redisServer;

2.每个客户端的目标数据库 默认是0号数据库 可以通过select命令进行切换
 struct redisClient {
    // 当前正在使用的数据库
    redisDb *db;  这个指针指向redisServer 中的db的一个元素
    }redisClient;
    
    
3.  数据库空间就是redis数据库 很多多redis的操作就是对数据库的键空间进行的操作
```java
/*数据库空间*/
/* Redis database representation. There are multiple databases identified
 * by integers from 0 (the default database) up to the max configured
 * database. The database number is the 'id' field in the structure. */
typedef struct redisDb {
    // 数据库键空间，保存着数据库中的所有键值对
    dict *dict;                 /* The keyspace for this DB */
    // 键的过期时间，字典的键为键，字典的值为过期事件 UNIX 时间戳  过期字典
    dict *expires;              /* Timeout of keys with a timeout set */
    // 正处于阻塞状态的键
    dict *blocking_keys;        /* Keys with clients waiting for data (BLPOP) */
    // 可以解除阻塞的键
    dict *ready_keys;           /* Blocked keys that received a PUSH */
    // 正在被 WATCH 命令监视的键
    dict *watched_keys;         /* WATCHED keys for MULTI/EXEC CAS */
    struct evictionPoolEntry *eviction_pool;    /* Eviction pool of keys */
    // 数据库号码
    int id;                     /* Database ID */
    // 数据库的键的平均 TTL ，统计信息
    long long avg_ttl;          /* Average TTL, just for stats */
} redisDb;
```

  注意在对是数据库的键空间进行操作的时候除了 本身对数据库的读写外 还有其他的一些维护操作比如
    更新服务的命中次数 不命中次数
    更新数据库键的LRU时间
    如果在读取的时候发现键过期 会进行删除
    如果有watch监听这个键 会标记为脏数据
    如果开启通知功能键进行修改后 会触发通知
    
    
3.如何设置redis键的过期时间
  expire key 1000   将key的生存时间设置为1000秒
  pexpire key 1000  将key的生存时间设置为1000毫秒
  expireat key 1000 将key的过期时间设置为1000秒这个时间点
  pexpireat key  10000 将key的过期时间设置为1000毫秒这个时间点  上面三个最终都是转化为这个方法实现的
  注意看上面的结构体redisDb 中的键过期时间

4.移除过期时间
  persist命令移除一个键的过期时间
  
5.计算返回键的剩余生存时间
  ttl  以秒为单位返回键的剩余生存时间
  pttl  以毫秒为单位返回键的剩余生存时间
  
6.我们晓得在读取键的时候需要判断键是否过期？ 怎么判断？
   检查给定的键是否存在过期字典 如果存在获取键的过期时间
   检查当前unix的时间戳是否大于键的过期时间 
   
7.什么时候删除过期的键？三种策略
 定时删除 主动删除 在创建过期时间的同时 创建定时器 到期删除
         节省内存 但是耗费CPU 特别是在大量并发的情况下 不合适
 定期删除 主动删除 每隔一段时间 扫描键删除
          比较好 但是难点在于确定执行的时间和频率
 惰性删除  被动删除 放任键不管 读键时候进行删除
  
 在实际中rdis采用定期删除和惰性删除结合的方式
   int expireIfNeeded(redisDb *db, robj *key) {}    //惰性删除
   void activeExpireCycle(int type) {} //定期删除  注意这个是随机进行执行 并不是一次会扫描完
  
  
  
8.注意redis的删除策略对主从复制 持久化AOF BDF  通知下的影响
  

   
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  