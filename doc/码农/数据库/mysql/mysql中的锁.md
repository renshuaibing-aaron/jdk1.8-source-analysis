数据库种的共享锁和排他锁 在Java种又叫读锁和写锁 其实都是悲观锁 即是悲观锁需要用数据本身锁来实现
乐观锁 是指在数据库表中增加一个版本号来实现

我们都知道innoDB引擎是用的行锁 其底层实现模式是加在索引上
例如 select * from table where id=1 for update  关键字for update 可以根据条件来完成加锁  如果id不是索引列 将使用表锁
Innodb 的锁的策略为 next-key 锁，即 record lock + gap lock ，是通过在 index 上加 lock 实现的。
如果 index 为 unique index ，则降级为 record lock 行锁。
如果是普通 index ，则为 next-key lock 
如果没有 index ，则直接锁住全表，即表锁

MyISAM 直接使用表锁



mysql中的
行锁  会出现死锁
表锁  不会出现死锁
页锁  会出现死锁
 
悲观锁 乐观锁  
说明 悲观锁和乐观锁的情况 悲观锁和乐观锁的情况 是锁表还是锁页

不同的存储引擎 对应的锁级别

什么条件下会产生死锁  怎么控制？
https://mp.weixin.qq.com/s?__biz=MzU2Njg3OTU1Mg==&mid=2247483909&idx=1&sn=57ab448ea1b95bc32b84bffa1d38f8c7&chksm=fca4f62acbd37f3c129f783a98919a4d71c8a943a27a06a0d2667e41aac06ec7cab463bc4f71&scene=27#wechat_redirect


什么条件下会出现死锁 ，因为MyISAM只支持表锁 所以针对死锁一般情况下都是由于InnoDB存储引擎引起的 
一般出现在两个Session的加锁顺序不一致导致的

什么是共享锁 什么是排他锁

在执行select的时候怎么加锁？或者说加锁的时机是什么时候？
  在数据库的增删改查的四种操作中 insert delete update都是会加排他锁 而select只有显式生命才会加锁
  select 最常见的查询 不加任何锁
  select ... lock in share mode 加共享锁
  select ... for update  加排他锁





































