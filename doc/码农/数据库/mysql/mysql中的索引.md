索引的数据结构采用的是B+树结构
什么是聚集索引 什么是非聚集索引
InnoDB有且只有一个聚簇索引，而MyISAM中都是非聚簇索引

所谓聚集索引就是把索引和数据在一起存放 B+树是一种聚集索引 找到了索引便找到了数据
非聚集索引是查找索引 需要回表 才可以查到具体的数据 为什么需要回表说明 叶子节点保存的不是数据 而是表的内存地址 然后进行二次查表即可


针对InnoDb存储引擎 的查找过程 现在非聚集索引里面查找  然后在叶子节点查找找到主键 然后再聚集索引里面找到值

select * from test where val=4 limit 300000,5
优化过后的代码
select * from test a inner join (select id from test where val=4 limit 300000,5) b on a.id=b.id;

分析为什么会提高搜索速度？
https://mp.weixin.qq.com/s?__biz=MzU2Njg3OTU1Mg==&mid=2247485312&idx=1&sn=001c4e6f714d682b9ab14eeed30d1e73&chksm=fca4f3afcbd37ab9d29ae2565d05fe5cb84616002787a078d5fc8789eaafa3540d1c54494d9f&scene=27#wechat_redirect

2.什么是最左前缀匹配原则？
对于复合索引 特别要注意索引失效的情况


3.深入理解InnoDB中的聚集索引和辅助索引(B+索引)
https://blog.csdn.net/wrs120/article/details/91126531
聚集索引：按照每张表的主键构成一棵B+树 叶子节点存放整张表的行记录数据

辅助索引：叶子节点并不包含行记录的全部数据

联合索引： 联合索引也是一个B+树 只不过键值的数量为多个 
(联合索引的最左匹配原则：最左优先 以最左为起点的任何连续索引都可以匹配的上 同时遇到> < between like就会停止匹配 )

覆盖索引：从辅助索引里面就可以得到查询的记录 而不需要查询聚集索引的记录

具体到sql执行过程中 采用何种索引有优化器说了算 优化器会根据具体的情况进行分析





























