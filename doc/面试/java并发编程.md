Unsafe  后门类，可以直接调用CPU指令(汇编语言)

java 源码解析

1.java 并发编程 锁的应用
ReentrantLock


2.JUC包中的类 
   ConcurrentHashMap 
   
3.红黑树 

4. B+树 


5.锁的应用

synchronized   以及锁的膨胀过程  偏向锁到轻量级锁到重量级锁
偏向锁->轻量级锁->重量级锁
   java join的作用
   
   重量级锁： 在加锁过程中每次都需要调用os的函数(在存多线程竞争锁资源的情况下)
   
   偏向锁：java 1.6之后对synchronized 进行了优化,在单线程不存在锁资源竞争的情况下 不再调用os函数  偏向锁里面有偏向的线程
   
   
   
   java 的wait方法 会让出CPU资源 和锁吗 然后变成重量级锁  等待别人唤醒
   
   
   
   
   
   
   






1.为什么喜欢问并发编程相关的知识?都包括哪些？
2.说说synchronized关键字的底层原理是什么？
jvm指令  monitor monitorexist 指令

3.能聊聊你对CAS的理解以及其底层实现原理可以吗？

4.ConcurrentHashMap实现线程安全的底层原理到底是什么？
就是问如何实现并发 JDK7 使用的是段锁 即是Segment 继承ReentrantLock  这样的并发写的能力取决于Segment的个数 有点重量级
JDk8 回归到HashMap的数据结构还是数据+ 链表 在put过程中 加入数组的位置为null 便利用CAS操作写入 ，否则对这个数组位置的数据加synchronized 锁 
减小了锁的粒度

5.你对JDK中的AQS理解吗？AQS的实现原理是什么
ReentrantLock 和synchronized的区别是什么？ AQS里面的核心变量是state=0 利用CAS进行更新  AQS里面有个队列
公平锁和非公平锁的区别

6.说说线程池的底层工作原理可以吗
里面肯定有个阻塞队列(无界或则无界队列)  这里说明的是 当核心线程的任务执行完以后都会阻塞在这个阻塞队列上获取任务
核心配置参数 ？平时我们应该怎么用？
首先创建核心线程数  然后新来的任务会进去阻塞队列里面 假如是有界队列，队列满了，然后会创建额外的线程进行队列任务的消费 然后队列空之后进行 在空闲一段时间 进行自己销毁
还有拒绝策略
newFixedThreadPool 最常用的线程池 这样的话 这里面用的是无界队列 核心线程数和最大线程数是一样的 然后 有了任务就往队列里面扔
如果线上机器突然宕机，线程池的阻塞队列中的请求怎么办？ 阻塞队列里面的值肯定是丢失的   怎么解决 在数据库里面落库即可

 













































   
   
   
   