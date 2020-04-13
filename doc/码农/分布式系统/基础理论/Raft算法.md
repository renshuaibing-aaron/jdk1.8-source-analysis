  
4.Raft算法 
在RocketMq中的主从节点选举用的是这个算法  本质和zk的选举算法很相似 
三个角色Leader（领导者） Candidate（候选人）Follower（选民）

先选出leader  并且每个leder都有一个epoch 

https://segmentfault.com/a/1190000020960201?utm_source=tag-newest