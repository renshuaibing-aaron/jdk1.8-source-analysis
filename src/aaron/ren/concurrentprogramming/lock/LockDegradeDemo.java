package aaron.ren.concurrentprogramming.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ������������demo
 * @author renshuaibing
 */
public class LockDegradeDemo {
    private int i = 0;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writerLock = readWriteLock.writeLock();
    private Lock readLock = readWriteLock.writeLock();

    public void doSomething() {
        writerLock.lock();
        try {
            i++;
            readLock.lock();
        } finally {
            writerLock.unlock();
        }
        try {
            // ģ���������Ӳ���
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            if (i == 1) {
                System.out.println("i��ֵ��" + i);
            } else {
                System.out.println("i = " + i);
            }
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        LockDegradeDemo lockDegradeDemo = new LockDegradeDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                lockDegradeDemo.doSomething();
            }).start();
        }
    }
}
