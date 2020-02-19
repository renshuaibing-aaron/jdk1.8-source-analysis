package aaron.ren.notifyandwait;
import java.util.concurrent.TimeUnit;

public class WaitNotify {

    final static Object lock = new Object();

    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("�߳� A �ȴ�����");
                synchronized (lock) {
                    try {
                        System.out.println("�߳� A �õ�����");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("�߳� A ��ʼ�ȴ���������");
                        lock.wait();
                        System.out.println("��֪ͨ���Լ���ִ�� �� ��������������");
                    } catch (InterruptedException e) {
                        //�������߳��ж�
                    }
                }
            }
        }, "�߳� A");

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("�߳� B �ȴ���");
                synchronized (lock) {
                    System.out.println("�߳� B �õ�����");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                    }
                    lock.notify();
                    System.out.println("�߳� B ���֪ͨ Lock �����ĳ���߳�");
                }
            }
        }, "�߳� B");

        threadA.start();
        threadB.start();



    }


}
