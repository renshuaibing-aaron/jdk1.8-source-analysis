redis的架构模式
单实例
redis哨兵 简单来说这个保证了redis的高可用 在master宕机时会自动将slave提升为master
redis cluster  这个模式着眼于扩展性，单个redis内存不足时，使用cluster进行分片存储
redis master-slave 主从复制的过程

redis的主从复制类型根据不同的时机可以分为两种情况 
1.全量复制 发生时间在slave初始化阶段 这个时候slave 从节点需要将master 主节点的数据都复制一份 具体步骤
  从服务器连接主服务器 发送SYNC命令
  主服务器接收到SYNC命令后，执行bgsave命令生成rdb文件，并且使用缓冲区疾苦此后执行的所有写命令
    主服务器BGSAVE执行完后，向所有从服务器发送快照文件，并在发送期间继续记录被执行的写命令； 
  从服务器收到快照文件后丢弃所有旧数据，载入收到的快照； 
  主服务器快照发送完毕后开始向从服务器发送缓冲区中的写命令； 
   从服务器完成对快照的载入，开始接收命令请求，并执行来自主服务器缓冲区的写命令；

2.增量复制
  Redis增量复制是指Slave初始化后开始正常工作时主服务器发生的写操作同步到从服务器的过程。 
  增量复制的过程主要是主服务器每执行一个写命令就会向从服务器发送相同的写命令，从服务器接收并执行收到的写命令。
  
  
2.讲讲redis的Hash槽的概念
  redis集群最早 没有原生的集群方案 当时采用的是proxy机制,就是需要依赖一个中间件, 其实不论是中间件还是原生的集群方案都需要
  解决一个寻址的问题，否则再集群中动态库容，导致失效 ，缓存击穿问题
   一般的解决方案是一致性Hash算法  再redis里面是自己实现的 用的是hash槽的概念 一共有16384个槽 然后用key%16384  
   进行寻址，不同的是原生的集群中的每个节点保留了整个集群的服务节点的消息 


3.为什么会有主从复制，主从复制中有哪些问题，是如何解决的?哨兵模式的原理？
https://mp.weixin.qq.com/s?__biz=MzU2Njg3OTU1Mg==&mid=2247485201&idx=1&sn=ecee56a240b59fd6df7b070c1138c5a6&chksm=fca4f33ecbd37a280f082306c02df9f2389a2effb1482e889c5167570ca8e1da30d5d618773c&scene=27#wechat_redirect



4.哨兵模式
  

4.集群
  集群中的复制和故障转移
  集群中的复制使用方法大致和主备复制是一样的 采用的命令
  故障转移是指集群中的每一个节点会发送ping命令到集群的其他节点 一旦发现有节点下线会进行重新选举和故障转移