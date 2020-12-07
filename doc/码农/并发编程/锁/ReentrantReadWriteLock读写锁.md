1.在并发环境中 用锁 一般可以用synchronized  但是不太好 不是因为锁太重量级 而是因为
这会导致资源的串行化访问 降低程序的效率
 解决办法 用读写锁 (为什么不用concurrentHash 可以用 但是在代码里面不只有一个资源 , 对于其他的资源怎么访问？还不是得用锁)
  特别是   在注册中心的用的比较常见
  
2.ReentrantReadWriteLock的核心原理 
 看代码可以知道读写锁表面来看是两个锁一个读锁 一个写锁 其实本质是一个锁 两个视图 其实本质是一个syn 也就是读写锁共用一个syn
 是AQS  
 用sate的高16位用来表示读锁的线程数 
  低16位表示写锁线程的进入次数

看源码可以知道 读写锁里面有个 final Sync sync; 变量在new ReentrantReadWriteLock 时候进行初始化 然后
读写锁里面有两个锁(读写锁读写锁用的是sync这个变量需要 对其进行操作)

3.读写锁的锁降级
 ```java
class CachedData {
   Object data;
   volatile boolean cacheValid;
   final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

   void processCachedData() {
     rwl.readLock().lock();
     if (!cacheValid) {
       // Must release read lock before acquiring write lock
       rwl.readLock().unlock();
       rwl.writeLock().lock();
       try {
         // Recheck state because another thread might have
         // acquired write lock and changed state before we did.
         if (!cacheValid) {
           data = ...
           cacheValid = true;
         }
         //注意这句话 说的很清楚 降级锁的过程是 通过在释放写锁之前 获取读锁获取的
         // Downgrade by acquiring read lock before releasing write lock
         rwl.readLock().lock();
       } finally {
         rwl.writeLock().unlock(); // Unlock write, still hold read
       }
     }

     try {
       use(data);
     } finally {
       rwl.readLock().unlock();
     }
   }
 }
```

思考两个问题 这里为什么要获取读锁  ？保证数据的可见性 这里主要是防止 如果不加读锁 有可能被别的线程修改了 
为了保证线程的可见性 加volatile 是否可行？ 不行 即使加了volatile 只是保证数据的可见性   这里需要的是一致性 这中间 缓存数据不能修改


3.参考资料
https://www.zhihu.com/question/265909728
