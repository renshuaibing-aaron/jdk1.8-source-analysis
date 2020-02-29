package aaron.ren.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    final static ReentrantLock lock = new ReentrantLock();


    static int a = 12;

    public static void main(String[] args) {

        thread1.start();
        thread2.start();
        thread3.start();

    }


    private static void sysMethod() {
        lock.lock();//��ȡ��
        try {
            a++;//ҵ���߼�
        } catch (Exception e) {
        } finally {
            lock.unlock();//�ͷ���
        }
    }

    static Thread thread1=new Thread(new Runnable() {
        @Override
        public void run() {
            sysMethod();
        }
    });
    static  Thread thread2=new Thread(new Runnable() {
        @Override
        public void run() {
            sysMethod();
        }
    });
    static  Thread thread3=new Thread(new Runnable() {
        @Override
        public void run() {
            sysMethod();
        }
    });

}
