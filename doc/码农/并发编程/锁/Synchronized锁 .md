1.Synchronized的三种使用
  ```java
  //修饰静态方法
 public static synchronized void test1() {

    }
```
  ```java
  //修饰实例方法
 public synchronized void test1() {
    }
```
```java
//修饰同步代码块  对于修饰的同步代码块进行反编译 明显可以看出 在同步代码块的两端生成了两条jvm指令 : monitorenter  和monitorexit 
   synchronized(this) {
        }
```

同步方法和同步块，哪个是更好的选择？
同步块是更好的选择，因为它不会锁住整个对象（当然你也可以让它锁住整个对象）。同步方法会锁住整个对象，哪怕这个类中有多个不相关联的同步块，这通常会导致他们停止执行并需要等待获得这个对象上的锁。
同步块更要符合开放调用的原则，只在需要锁住的代码块锁住相应的对象，这样从侧面来说也可以避免死锁。
在监视器(Monitor)内部，是如何做线程同步的？
监视器和锁在 Java 虚拟机中是一块使用的。监视器监视一块同步代码块，确保一次只有一个线程执行同步代码块。每一个监视器都和一个对象引用相关联。线程在获取锁之前不允许执行同步代码。


1.java 中的锁分为内部锁和显示锁
  https://www.jianshu.com/p/c5058b6fe8e5
  https://www.jianshu.com/p/4758852cbff4
  https://www.jianshu.com/p/6f0bba673d5c
  https://www.cnblogs.com/linghu-java/p/8944784.html
  https://www.cnblogs.com/aobing/p/12906927.html
说说内部锁是怎么优化的？ (分析synchronized锁的粗化过程 需要看JVM虚拟机的源码来解释过程)
  其实synchronized锁共有四种状态从低到高是无锁状态->  偏向状态-> 轻量级状态 -> 重量级状态  (锁粗化/膨胀的过程) 并且这中间不能降级

锁的消除 
锁粗化
偏向锁
适应锁

synchronized的执行过程： 
1. 检测Mark Word里面是不是当前线程的ID，如果是，表示当前线程处于偏向锁 
2. 如果不是，则使用CAS将当前线程的ID替换Mard Word，如果成功则表示当前线程获得偏向锁，置偏向标志位1 
3. 如果失败，则说明发生竞争，撤销偏向锁，进而升级为轻量级锁。 
4. 当前线程使用CAS将对象头的Mark Word替换为锁记录指针，如果成功，当前线程获得锁 
5. 如果失败，表示其他线程竞争锁，当前线程便尝试使用自旋来获取锁。 
6. 如果自旋成功则依然处于轻量级状态。 
7. 如果自旋失败，则升级为重量级锁。

2.Java中锁 主要讲Synchronized的锁的变化 这个需要搞清楚这个锁的底层是怎么实现的 查看hotpot 虚拟机源码 
也即是Synchronized在翻译成JVM指令后 在Hotpot在执行过程中 怎么调用底层来实现锁的变化
https://www.jianshu.com/p/c5058b6fe8e5



1.Synchronized  的基本使用 
 修饰普通方法 
 修饰静态方法
 修饰代码块
 
 2.说明三种使用方式的底层原理
 https://www.jianshu.com/p/c5058b6fe8e5
 
 对于修饰的同步代码块进行反编译 明显可以看出 在同步代码块的两端生成了两条jvm指令 : monitorenter  和monitorexit 
 通过查找JVM规范说明对于synchronized的语义底层时通过一个monitor来完成的可以知道 每个对象都有一个监视器锁 monitor 当monitor被占用时 就处于被锁定状态
  其实对于wait和notify等方法也是依赖monitor
 
 对于修饰方法的同步 JVM的实现用的不是字节码指令 其常量池中多了ACC_SYNCHRONIZED 来标识
 JVM根据这个标识来实现方法的同步 当方法调用时 检查方法的访问标志是否被设置
 
 3.著名的锁Synchronized升级和降级
 