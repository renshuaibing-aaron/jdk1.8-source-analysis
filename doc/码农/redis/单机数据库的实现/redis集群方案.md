1.创建集群方法利用命令 先启动7000端口的服务器 然后在7000的服务器上执行命令
   cluster meet 127.0.0.1 7001
   cluster meet 127.0.0.1 7002
   
2.启动节点
   启动redis节点 redis服务会根据cluster-enabled配置参数决定redis服务器以什么角色启动
   
3.redis集群的基本原理,redis集群一共是两个核心技术(节点主从+一致性hash)
  节点主从和zk相比
 