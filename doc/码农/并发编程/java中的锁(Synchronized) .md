1.Synchronized的三种使用
  ```java
 public static synchronized void test1() {

    }

```
  ```java
 public synchronized void test1() {

    }
```
```java
   synchronized(this) {

        }
```

同步方法和同步块，哪个是更好的选择？
同步块是更好的选择，因为它不会锁住整个对象（当然你也可以让它锁住整个对象）。同步方法会锁住整个对象，哪怕这个类中有多个不相关联的同步块，这通常会导致他们停止执行并需要等待获得这个对象上的锁。
同步块更要符合开放调用的原则，只在需要锁住的代码块锁住相应的对象，这样从侧面来说也可以避免死锁。
在监视器(Monitor)内部，是如何做线程同步的？
监视器和锁在 Java 虚拟机中是一块使用的。监视器监视一块同步代码块，确保一次只有一个线程执行同步代码块。每一个监视器都和一个对象引用相关联。线程在获取锁之前不允许执行同步代码。


1.java 中的锁分为内部锁和显示锁
说说内部锁是怎么优化的？

Synchronized
锁的消除 
锁粗化
偏向锁
适应锁

2.Java中锁 主要讲Synchronized的锁的变化 这个需要搞清楚这个锁的底层是怎么实现的 查看hotpot 虚拟机源码 
也即是Synchronized在翻译成JVM指令后 在Hotpot在执行过程中 怎么调用底层来实现锁的变化
https://www.jianshu.com/p/c5058b6fe8e5



1.Synchronized  的基本使用 
 修饰普通方法 
 修饰静态方法
 修饰代码块
 
 2.说明三种使用方式的底层原理
 
 对于修饰的同步代码块进行反编译 明显可以看出 在同步代码块的两端生成了两条jvm指令 
 monitorenter  和monitorexit 
 通过查找JVM规范说明对于synchronized的语义底层时通过一个monitor来完成的可以知道 每个对象都有一个监视器锁 monitor 当monitor被占用时 就处于被锁定状态
  其实对于wait和notify等方法也是依赖monitor
 
 对于修饰方法的同步 JVM的实现用的不是字节码指令 其常量池中多了ACC_SYNCHRONIZED 来标识
 JVM根据这个标识来实现方法的同步 当方法调用时 检查方法的访问标志是否被设置
 
 3.著名的锁Synchronized升级和降级
 