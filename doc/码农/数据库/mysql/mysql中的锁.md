mysql中的
行锁  会出现死锁
表锁  不会出现死锁
页锁  会出现死锁
 
悲观锁 乐观锁  

不同的存储引擎 对应的锁级别

什么条件下会产生死锁  怎么控制？
https://mp.weixin.qq.com/s?__biz=MzU2Njg3OTU1Mg==&mid=2247483909&idx=1&sn=57ab448ea1b95bc32b84bffa1d38f8c7&chksm=fca4f62acbd37f3c129f783a98919a4d71c8a943a27a06a0d2667e41aac06ec7cab463bc4f71&scene=27#wechat_redirect


什么条件下会出现死锁 ，因为MyISAM制支持表锁 所以针对死锁一般情况下都是由于InnoDB存储引擎引起的 
一般出现在两个Session的加锁顺序不一致导致的