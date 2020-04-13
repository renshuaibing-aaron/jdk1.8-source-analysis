1.Hystrix的特性
断路器机制  可以计算线路的健康度 进行短路器的设置
Fallback(失败回退)
资源隔离机制  主要有两种方式 线程池的隔离 信号量的隔离 依赖隔离
执行模型  同步执行 异步执行 reactive模式执行 observe和toObservable
运维平台 基础的Dasgboard和整合Turbine
缓存
请求合并 HystrixCollapser


2.源码https://github.com/Netflix/Hystrix.git


3.详细介绍
https://www.cnblogs.com/cjsblog/p/9395584.html


Hystrix主要有4种调用方式：
toObservable() 方法 ：未做订阅，只是返回一个Observable 。
observe() 方法 ：调用 #toObservable() 方法，并向 Observable 注册 rx.subjects.ReplaySubject 发起订阅。
queue() 方法 ：调用 #toObservable() 方法的基础上，调用：Observable#toBlocking() 和 BlockingObservable#toFuture() 返回 Future 对象
execute() 方法 ：调用 #queue() 方法的基础上，调用 Future#get() 方法，同步返回 #run() 的执行结果。
主要的执行逻辑：
1.每次调用创建一个新的HystrixCommand,把依赖调用封装在run()方法中.

2.执行execute()/queue做同步或异步调用.

3.判断熔断器(circuit-breaker)是否打开,如果打开跳到步骤8,进行降级策略,如果关闭进入步骤.

4.判断线程池/队列/信号量是否跑满，如果跑满进入降级步骤8,否则继续后续步骤.

5.调用HystrixCommand的run方法.运行依赖逻辑

依赖逻辑调用超时,进入步骤8.

6.判断逻辑是否调用成功。返回成功调用结果；调用出错，进入步骤8.

7.计算熔断器状态,所有的运行状态(成功, 失败, 拒绝,超时)上报给熔断器，用于统计从而判断熔断器状态.

8.getFallback()降级逻辑。以下四种情况将触发getFallback调用：

run()方法抛出非HystrixBadRequestException异常。
run()方法调用超时
熔断器开启拦截调用
线程池/队列/信号量是否跑满
没有实现getFallback的Command将直接抛出异常，fallback降级逻辑调用成功直接返回，降级逻辑调用失败抛出异常.
