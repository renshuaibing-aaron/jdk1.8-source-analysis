zookeeper-选举源码分析
https://segmentfault.com/a/1190000019197520?utm_source=tag-newest
QuorumCnxManager 类主要职能是负责集群中节点与外部节点进行通信及投票信息的中转
FastLeaderElection 类是选举投票的核心实现
选举投票规则

比较外部投票与内部投票的选举周期值，选举周期大的值优先
若选举周期值一致，则比较事务 ID； 事务 ID 最新的优先
若选举周期值一致且事务 ID 值相同，则比较投票节点的 server id; server id 最大的优先
集群中节点通信时为了避免重复建立连接，遵守一个原则：连接总是由 server id 较大的一方发起