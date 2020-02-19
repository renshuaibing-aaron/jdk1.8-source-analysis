1.说说你对Spring的 IOC 机制的理解可以吗
控制反转和依赖注入
2.说说你对Spring的AOP机制的理解可以吗
3.了解过cglib动态代理吗？他跟jdk动态代理的区别是什么


4.能说说Spring中的Bean是线程安全的吗
spring的生命周期 ？作用域？
（1）singleton：默认，每个容器中只有一个bean的实例  单例模式
（2）prototype：为每一个bean请求提供一个实例
一般来说下面几种作用域，在开发的时候一般都不会用，99.99%的时候都是用singleton单例作用域
（3）request：为每一个网络请求创建一个实例，在请求完成以后，bean会失效并被垃圾回收器回收
（4）session：与request范围类似，确保每个session中有一个bean的实例，在session过期后，bean会随之失效
（5）global-session
答案是否定的，绝对不可能是线程安全的，spring bean默认来说，singleton，都是线程不安全的，java web系统，
一般来说很少在spring bean里放一些实例变量，一般来说他们都是多个组件互相调用，最终去访问数据库的
说明因为spring bean的作用域大部分都是单例的，所以假如bean里面有个成员变量 那么肯定会有多个线程取访问该成员变量 在这种情况下
假如没有采用一些线程安全的保护(juc并发集合) 肯定会线程不安全


5.Spring的事务实现原理是什么？能聊聊你对事务传播机制的理解吗
 spring事务的理解
 
 6.能画一张图说说Spring Boot的核心架构吗？
 好处就是内嵌tomcat
 自动配置利用starter
 
 7.能画一张图说说Spring的核心架构吗 或者说spring的生命周期？
 

