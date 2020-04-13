package aaron.ren.concurrentprogramming.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    final Lock readLock = readWriteLock.readLock();
    final Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) throws InterruptedException {
        //        new ReadWriteLockTest().writeRead();
        //        new ReadWriteLockTest().readRead();
        //        new ReadWriteLockTest().readWrite();
        //        new ReadWriteLockTest().writeWrite();
        new ReadWriteLockTest().writeReadWrite();
    }

    /**
     * 读 -> 写。同时有写锁和读锁时，写锁必须阻塞等待读锁读完，写锁才能进去。
     * @throws InterruptedException
     */
    private void readWrite() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w请求读锁", Thread.currentThread().getId()));
                    readLock.lock();
                    System.out.println(String.format("tid[%s]: w获得读锁", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w释放读锁", Thread.currentThread().getId()));
                    readLock.unlock();
                }
            }
        }).start();
        // 为了保证顺序，休眠一会再调度
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w请求写锁", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w获得写锁", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w释放写锁", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
    }

    /**
     * 写 -> 读 -> 写。
     * 后面写锁是否会优先调度？答：不会，还是会按顺序调度
     * @throws InterruptedException
     */
    private void writeReadWrite() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w请求写锁", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w获得写锁", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w释放写锁", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
        // 为了保证顺序，休眠一会再调度
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w请求读锁", Thread.currentThread().getId()));
                    readLock.lock();
                    System.out.println(String.format("tid[%s]: w获得读锁", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w释放读锁", Thread.currentThread().getId()));
                    readLock.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w请求写锁", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w获得写锁", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w释放写锁", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
    }

    /**
     * 写 -> 读。同时有写锁和读锁时，读锁必须等待写锁写完后，才能获得锁
     * @throws InterruptedException
     */
    private void writeRead() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w请求写锁", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w获得写锁", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w释放写锁", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
        // 为了保证顺序，休眠一会再调度
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w请求读锁", Thread.currentThread().getId()));
                    readLock.lock();
                    System.out.println(String.format("tid[%s]: w获得读锁", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w释放读锁", Thread.currentThread().getId()));
                    readLock.unlock();
                }
            }
        }).start();
    }

    /**
     * 读 -> 读，读锁可以共享
     */
    private void readRead() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w请求读锁", Thread.currentThread().getId()));
                    readLock.lock();
                    System.out.println(String.format("tid[%s]: w获得读锁", Thread.currentThread().getId()));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w释放读锁", Thread.currentThread().getId()));
                    readLock.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w请求读锁", Thread.currentThread().getId()));
                    readLock.lock();
                    System.out.println(String.format("tid[%s]: w获得读锁", Thread.currentThread().getId()));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w释放读锁", Thread.currentThread().getId()));
                    readLock.unlock();
                }
            }
        }).start();
    }

    /**
     * 写 -> 写。同时有写锁和写锁时，写锁必须等待上一个写锁写完后，才能获得锁
     * @throws InterruptedException
     */
    private void writeWrite() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w请求写锁", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w获得写锁", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w释放写锁", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
        // 为了保证顺序，休眠一会再调度
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w请求写锁", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w获得写锁", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w释放写锁", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
    }

}


