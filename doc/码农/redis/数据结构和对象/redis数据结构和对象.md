1.注意三个基本的概念
redis中的数据结构(八种)
redis常见的数据类型(redis中封装的对象五种)
redis的指令(命令)



2.redis中的对象 也是一般博客里面提到的数据结构  redis一共提供五种对象
    字符串对象(String)
    常见命令
    使用场景 
      string作为redis中最常见的对象 用处范围比较广 
      string是二进制安全的数据结构 意味着 可以存储一些图片信息等
      分布式锁
      全局唯一ID生成
    底层数据结构  int raw  embstr


哈希对象(hash) 类似HashMap<String,HashMap<String,String>> h = new HashMap<>();
 常见命令
 使用场景 
  hash键可以信息凝聚在一起，而不是分散在整个redis中方便了数据管理
  避免键名冲突
  减少内存使用
 底层数据结构 ziplist或者hashtable


列表对象(list)
 常见命令
 使用场景 
 底层数据结构  ziplist或者linkedlist
 

集合对象(Set)  集合里面的数据结构 不允许重复  
常见命令 集合的命令主要特性在于求集合的并集  交集  差集(以第一个集合为准)
使用场景  
  直播刷礼物 转发微博抽奖活动
  点赞 签到
  关注模型
  电商中商品的筛选
底层数据结构
  集合对象底层采用的是hashtable或者intset


有序集合对象(Zset)  这个集合里面元素是有序的 每次插入的时候每个对象会有一个score值
常见命令 
使用场景
   使用场景主要使用score这个特性来使用   在有些时候 可以利用这个有序集合实现滑动窗口限流的作用
   主要场景是 排行榜
底层数据结构
   ziplist或者skiplist
   
   
3.redis中的八种底层数据结构
 https://stor.51cto.com/art/201910/605032.htm
   SDS simple synamic string：支持自动动态扩容的字节数组  这个里面又分为两种模式raw  embstr
   intset (整数集合)： 用于存储整数数值集合的自有结构 
   
   list(链表) ：链表 节点不直接存储数据 通过指针的形式间接持有
   zskiplist(跳跃表) ：附加了后向指针的跳跃表  著名的跳表 这个可以和红黑树进行比较
   ziplist(压缩列表) ：一种实现上类似于TLV, 但比TLV复杂的, 用于存储任意数据的有序序列的数据结构  
   quicklist：一种以ziplist作为结点的双链表结构, 实现的非常不错 
   
   dict(字典) ：使用双哈希表实现的, 支持平滑扩容的字典  采用链地址方法处理hash碰撞 
   zipmap ： 一种用于在小规模场合使用的轻量级字典结构 









redis相对于memcache 的最大优势就是丰富的数据结构类型具体有
字符串String 最基本的数据类型，二进制安全的字符串，最大512M
字典Hash key-value对的一种集合
列表List  按照添加顺序保持顺序的字符串列表
集合Set 无序的字符串集合，不存在重复的元素
有序集合SortedSet 已排序的字符串集合
bitmap：更细化的一种操作，以bit为单位
HyperLogLog 基于概率的数据结构
Geo
Pub/Sub

Redis Module

BloomFilter

RedisSearch

Redis-ML

假如redis里面有一亿个key 怎么把他们找出来？
利用keys 和scan指令 说明这两个命令的区别：keys在执行过程中会导致线程的停顿，因为redis是单线程的 异步的方式执行可以使用scan命令


利用redis实现异步的消息队列怎么使用？



2.redis如何查询百万的数据量 比较keys和scan的区别

