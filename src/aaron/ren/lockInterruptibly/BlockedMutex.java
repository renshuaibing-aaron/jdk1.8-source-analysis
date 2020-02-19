package aaron.ren.lockInterruptibly;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockedMutex {
    private Lock lock = new ReentrantLock();


    public BlockedMutex() {
        this.lock.lock();// mian�̳߳����������
        System.out.println(Thread.currentThread().getName());
    }

    public void f() {
        try {
            this.lock.lockInterruptibly();// �����ǰ�߳�δ���ж����ȡ��
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
            System.out.println("Interrupted from lock acquisition in f()");
        }
    }
}