package aaron.ren.lock.lockInterruptibly;

import java.util.concurrent.TimeUnit;

public class Interrupting2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}
/*

打印结果：

        main
        Waiting for f() in BlockedMutex
        Issuing t.interrupt()
        Interrupted from lock acquisition in f()
        Broken out of blocked call
        Thread-0

        可知，?Thread t = new Thread(new Blocked2())中的new Blocked2()的lock被主线程锁持有，且不释放，
        子线程调用blocked.f()方法而被阻塞，mian线程休眠1s，中断子线程，就产生了以上打印效果，
        说明了ReentrantLock是可中断的，区别于关键字锁的不可中断性质。
*/
