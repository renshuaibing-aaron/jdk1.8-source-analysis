1.netty 中的boss线程数量设置1或者其他有区别吗？
 基于netty的nio服务端程序，只监听一个端口的连接请求，如果bossGroups设置多个线程数量是不是浪费资源？ 
 因为服务端的NiOServerSocketChannel只注册到了一个eventLoop中，也就只有一个线程在处理连接请求
 
 
2.netty中的work线程怎么设置？
https://www.jianshu.com/p/a6d2baf09897
https://www.cnblogs.com/crazymakercircle/archive/2018/11/05/9911981.html
  work线程数量的设置我觉得需要根据具体的应用设置 一般来说根据CPU核数有关
  CPU密集型：线程池设置为N+1 (CPU密集型 防止多线程之间的切换 不需要太多的线程)
  IO密集型：线程池为2N+1 
boss线程池和worker线程池能不能合在一起

https://www.cnblogs.com/luoxn28/p/11875340.html
