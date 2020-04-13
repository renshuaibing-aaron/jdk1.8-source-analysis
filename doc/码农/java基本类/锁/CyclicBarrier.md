1.CyclicBarrier  这个类也是锁 根据JDK上面的注释的意思是
同步帮助，允许一组线程互相等待，以达到共同的障碍点  其实是一组线程相互等待 线程都到达一个点 然后进行下轮开始 
和countdownlatch相比 有很大的区别

countdownlatch不能重复使用 这个本质是  其他的线程可以修改这个值 (AQS里面有个初始的值)  当这个值 到0的时候 这个线程 继续执行
所以这countdownLatch翻译成倒数计时器 
而cylicbarrier这个 是栅栏 ，也就是拦截的是一批线程,线程只有都到了 这一批线程才开始往下继续执行 ，并且会有个回调函数(可用可不用)

CountDownLatch 是一次性的，CyclicBarrier 是可循环利用的
CountDownLatch 参与的线程的职责是不一样的，有的在倒计时，有的在等待倒计时结束。CyclicBarrier 参与的线程职责是一样的。


CyclicBarrier ，一个同步辅助类，在 AP I中是这么介绍的：

它允许一组线程互相等待，直到到达某个公共屏障点 (Common Barrier Point)。在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，
此时 CyclicBarrier 很有用。因为该 Barrier 在释放等待线程后可以重用，所以称它为循环( Cyclic ) 的 屏障( Barrier ) 。

通俗点讲就是：让一组线程到达一个屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活

