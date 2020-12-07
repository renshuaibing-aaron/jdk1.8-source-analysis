1.实例代码
```java
public class WaitNotifyCase {
    public static void main(String[] args) {
        final Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread A is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("thread A get lock");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("thread A do wait method");
                        //表示线程执行lock.wait()方法时，必须持有该lock对象的monitor，如果wait方法在synchronized代码中执行，
                        // 该线程很显然已经持有了monitor
                        lock.wait();
                        //wait方法会将当前线程放入wait set，等待被唤醒，
                        // 并放弃lock对象上的所有同步声明，意味着线程A释放了锁，线程B可以重新执行加锁操作
                        System.out.println("wait end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread B is waiting to get lock");
                synchronized (lock) {
                    System.out.println("thread B get lock");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //notify方法会选择wait set中任意一个线程进行唤醒
                    lock.notify();
                    //notifyAll方法会唤醒monitor的wait set中所有线程。
                    lock.notifyAll();
                    //执行完notify方法，并不会立马唤醒等待线程，在notify方法后面加一段sleep代码就可以看到效果，如果线程B执行完notify方法之后sleep 5s，在这段时间内，线程B依旧持有monitor，线程A只能继续等待；
                    System.out.println("thread B do notify method");
                }
            }
        }).start();
    }
}
```




3.wait()和sleep()的区别
  如果了解两个方法的底层很好回答 wait()是object的方法 在执行wait方法之前需要加锁 然后释放锁 进入对象的等待队列 也就是说这个wait()方法其实内部(JVM内部有操作)
  sleep()是Thread的静态方法 底层调用的是个native 本地方法 在执行的时候 线程会停止 但是不会释放资源(一些锁资源) 这个很容易解释
  sleep() 只是线程停止 没有其他操作
  

 5.应该在循环中判断等待条件 
   处于等待状态的线程可能会收到错误警报和伪唤醒，如果不在循环中检查等待条件，程序就会在没有满足结束条件的情况下退出
   如果不在循环中检查等待条件，程序就会在没有满足结束条件的情况下退出。因此，当一个等待线程醒来时，不能认为它原来的等待状态仍然是有效的，
   在notify()方法调用之后和等待线程醒来之前这段时间它可能会改变。这就是在循环中使用wait()方法效果更好的原因
 ```java
// The standard idiom for using the wait method
synchronized (obj) {
    while (condition does not hold) {
        obj.wait(); // (Releases lock, and reacquires on wakeup)
    }
    ... // Perform action appropriate to condition
}
```


2.
    //这个还是没有解释 为什么需要加锁
    
   进入wait/notify方法之前，为什么要获取synchronized锁？ 这个其实跟wait的底层实现原理有关 通过锁的竞争实现 等待/唤醒操作
    简单的说 syn锁编译后会形成monitor对象然后 在竞争锁的时候其实 每个锁都有两个队列一个入口队列和一个等待队列
    wait() 方法的本质就是把这个线程放进等待队列  在对这个等待队列进行操作的时候必须加锁 入队列后会进行 释放锁
  线程A获取了synchronized锁，执行wait方法并挂起，线程B又如何再次获取锁？
     //另外一种学说任务 之所以加锁是因为防止丢失唤醒问题 或者是线程饥饿
     https://www.cnblogs.com/chen--biao/p/11358016.html
     https://blog.csdn.net/WenWu_Both/article/details/106520799
     
 本质原因是这个 防止丢失唤醒 或者是线程饥饿  
  

// 线程A 的代码
while(!condition){ // 不能使用 if , 因为存在一些特殊情况， 使得线程没有收到 notify 时也能退出等待状态
    wait();
}
// do something

// 线程 B 的代码
if(!condition){
    // do something ...
    condition = true;
    notify();
}
现在考虑， 如果wait() 和 notify() 的操作没有相应的同步机制， 则会发生如下情况

【线程A】 进入了 while 循环后（通过了 !condition 判断条件， 但尚未执行 wait 方法）, CPU 时间片耗尽， CPU 开始执行线程B的代码
【线程B】 执行完毕了 condition = true; notify(); 的操作， 此时【线程A】的 wait() 操作尚未被执行， notify() 操作没有产生任何效果
【线程A】执行wait() 操作， 进入等待状态，如果没有额外的 notify() 操作， 该线程将持续在 condition = true 的情形下， 持续处于等待状态得不到执行。

