1.dubbo  的工作原理 ？ 注册中心挂了 还可以继续通信吗？说一下rpc的请求的流程
注册中心挂了 还是可以继续提供服务的 因为有缓存的存在

2.dubbo 都支持那些通信协议 支持哪些序列化协议

dubbo协议 单一长连接协议 序列化协议是hessian  说明这个consumer和provider保持长连接 正在每次进行通信前 已经建立好了连接


3.dubbo支持哪些负载均衡策略 高可用和动态代理策略

dubbo 集群容错策略  快速失败 失败自动切换  异常忽略 并行调用等


动态代理策略：默认使用javassisit动态字节码生成 创建代理类 但是可以通过spi扩展机制配置自己的动态代理策略

4.spi是什么东西 dubbo的spi机制是怎么使用的

5.dubbo如何做服务治理服务降级 以及重试(失败重试 超时重试)


6.分布式系统里面的接口调用顺序如何保证

6.分布式系统里面的接口的幂等性如何保证(比如保证不能重复扣款)

6.如何设计一个类似dubbo的rpc框架  架构上如何考虑？