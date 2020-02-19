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

