1.ReentrantLock  可重入 可中断的锁

2.重要的锁实现
https://www.jianshu.com/p/1014fdd375cf  
Condition配合ReentrantLock可以实现synchronized的wait、notify类似的功能。并且功能比synchronized更加强大，能够实现中断、限时



3.讲一讲condition的底层原理
  条件队列和阻塞队列的节点，都是 Node 的实例，因为条件队列的节点是需要转移到阻塞队列中去的；
     我们知道一个 ReentrantLock 实例可以通过多次调用 newCondition() 来产生多个 Condition 实例，
  这里对应 condition1 和 condition2。注意，ConditionObject 只有两个属性 firstWaiter 和 lastWaiter；
     每个 condition 有一个关联的条件队列，如线程 1 调用 condition1.await() 方法即可将当前线程 1 包装成 Node 后加入到条件队列中，
     然后阻塞在这里，不继续往下执行，条件队列是一个单向链表；
  调用condition1.signal() 触发一次唤醒，此时唤醒的是队头，会将condition1 对应的条件队列的 
    firstWaiter（队头） 移到阻塞队列的队尾，等待获取锁，获取锁后 await 方法才能返回，继续往下执行。