rocketMq是如何基于Netty扩展高性能网络通信架构？
本质就是采用多个线程隔离的模式  具体就是Reactor主线程在端口上监听Producer建立连接的请求 建立长连接
Reactor线程池并发的监听多个连接的请求是否达到
worker请求并发的对多个请求进行预处理
业务线程池并发的对多个请求进行磁盘的读写业务操作