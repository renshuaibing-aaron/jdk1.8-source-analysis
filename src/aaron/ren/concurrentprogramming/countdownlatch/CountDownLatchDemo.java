package aaron.ren.concurrentprogramming.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo implements Runnable{

    static final CountDownLatch latch = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        // ģ��������
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //������һ
            //����finally��������ִ�й��̳����쳣������countDown()���ܱ�ִ��
            latch.countDown();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i=0; i<10; i++){
            exec.submit(demo);
        }

        // �ȴ����
        latch.await();

        // ������
        System.out.println("Fire!");
        // �ر��̳߳�
        exec.shutdown();
    }
}