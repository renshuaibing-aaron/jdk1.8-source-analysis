package aaron.ren.lock.lockInterruptibly;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockedMutex {
    private Lock lock = new ReentrantLock();


    public BlockedMutex() {
        this.lock.lock();// mian线程持有了这个锁
        System.out.println(Thread.currentThread().getName());
    }

    public void f() throws Exception {
        try {
            this.lock.lockInterruptibly();// 如果当前线程未被中断则获取锁
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
            System.out.println("Interrupted from lock acquisition in f()");
            throw new Exception("是否体检不能为空");
        }
    }
}