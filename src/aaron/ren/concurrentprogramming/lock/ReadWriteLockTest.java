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
     * �� -> д��ͬʱ��д���Ͷ���ʱ��д�����������ȴ��������꣬д�����ܽ�ȥ��
     * @throws InterruptedException
     */
    private void readWrite() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w�������", Thread.currentThread().getId()));
                    readLock.lock();
                    System.out.println(String.format("tid[%s]: w��ö���", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w�ͷŶ���", Thread.currentThread().getId()));
                    readLock.unlock();
                }
            }
        }).start();
        // Ϊ�˱�֤˳������һ���ٵ���
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w����д��", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w���д��", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w�ͷ�д��", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
    }

    /**
     * д -> �� -> д��
     * ����д���Ƿ�����ȵ��ȣ��𣺲��ᣬ���ǻᰴ˳�����
     * @throws InterruptedException
     */
    private void writeReadWrite() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w����д��", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w���д��", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w�ͷ�д��", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
        // Ϊ�˱�֤˳������һ���ٵ���
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w�������", Thread.currentThread().getId()));
                    readLock.lock();
                    System.out.println(String.format("tid[%s]: w��ö���", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w�ͷŶ���", Thread.currentThread().getId()));
                    readLock.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w����д��", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w���д��", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w�ͷ�д��", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
    }

    /**
     * д -> ����ͬʱ��д���Ͷ���ʱ����������ȴ�д��д��󣬲��ܻ����
     * @throws InterruptedException
     */
    private void writeRead() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w����д��", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w���д��", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w�ͷ�д��", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
        // Ϊ�˱�֤˳������һ���ٵ���
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w�������", Thread.currentThread().getId()));
                    readLock.lock();
                    System.out.println(String.format("tid[%s]: w��ö���", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w�ͷŶ���", Thread.currentThread().getId()));
                    readLock.unlock();
                }
            }
        }).start();
    }

    /**
     * �� -> �����������Թ���
     */
    private void readRead() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w�������", Thread.currentThread().getId()));
                    readLock.lock();
                    System.out.println(String.format("tid[%s]: w��ö���", Thread.currentThread().getId()));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w�ͷŶ���", Thread.currentThread().getId()));
                    readLock.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w�������", Thread.currentThread().getId()));
                    readLock.lock();
                    System.out.println(String.format("tid[%s]: w��ö���", Thread.currentThread().getId()));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w�ͷŶ���", Thread.currentThread().getId()));
                    readLock.unlock();
                }
            }
        }).start();
    }

    /**
     * д -> д��ͬʱ��д����д��ʱ��д������ȴ���һ��д��д��󣬲��ܻ����
     * @throws InterruptedException
     */
    private void writeWrite() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w����д��", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w���д��", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w�ͷ�д��", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
        // Ϊ�˱�֤˳������һ���ٵ���
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(String.format("tid[%s]: w����д��", Thread.currentThread().getId()));
                    writeLock.lock();
                    System.out.println(String.format("tid[%s]: w���д��", Thread.currentThread().getId()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(String.format("tid[%s]: w�ͷ�д��", Thread.currentThread().getId()));
                    writeLock.unlock();
                }
            }
        }).start();
    }

}


