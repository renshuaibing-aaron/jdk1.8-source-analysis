1.首先明白AQS的基本原理
AQS 全名叫抽象队列同步器 就是并发包的基础组件

AQS类里核心组件 state变量 加锁的线程 等待队列   通过这些核心的组件用来实现各种锁和各种同步组件

2.需要掌握Condition功能的实现


3.AQS中主要的是state状态的获取  这个是核心资源 著名的读写锁的获取 就是用这个state的前16位和后16位保持不同的作用

4.了解AQS的主要成员变量

    // 头结点，你直接把它当做 当前持有锁的线程 可能是最好理解的
    private transient volatile Node head;
    
    // 阻塞的尾节点，每个新的节点进来，都插入到最后，也就形成了一个链表
    private transient volatile Node tail;
    
    // 这个是最重要的，代表当前锁的状态，0代表没有被占用，大于 0 代表有线程持有当前锁
    // 这个值可以大于 1，是因为锁可以重入，每次重入都加上 1
    private volatile int state;
    
    // 代表当前持有独占锁的线程，举个最重要的使用例子，因为锁可以重入
    // reentrantLock.lock()可以嵌套调用多次，所以每次用这个来判断当前线程是否已经拥有了锁
    // if (currentThread == getExclusiveOwnerThread()) {state++}
    private transient Thread exclusiveOwnerThread; //继承自AbstractOwnableSynchronizer  注意这变量的位置不再AQS里面 在AOS里面
