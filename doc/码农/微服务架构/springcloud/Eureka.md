1.微服务注册中心如何承载大型系统的千万级访问

这个和eureka的数据结构有关 eureka是基于纯内存的操作 + 多级缓存 加上独特的心跳机制 保证了日千万级的访问
这里需要说明的是eureka的client 每隔30秒发送心跳到eureka server
eureka client每隔30秒去找eureka拉取最新的注册表的变化 看看其他的服务地址有没有变化
 这里说明的是eureka clint里面有一个缓存保存了 各个服务的地址 
 前端调用网关到 具体的微服务 然后微服务 利用自己client里面缓存的信息进行 调用