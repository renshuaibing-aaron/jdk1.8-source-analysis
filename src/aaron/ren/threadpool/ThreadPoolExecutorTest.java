package aaron.ren.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor������
 * ע�⣺
 * 1��ThreadPoolExecutor��һ���̳߳�
 * 2��������񶼿����ɸ��̳߳���ѡ�������߳���ִ��
 */
public class ThreadPoolExecutorTest {
    private static ThreadPoolExecutor executor =
            new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public void executeTask(){
        Task1 task1 = new Task1();//��������1
        Task2 task2 = new Task2();//��������2
        executor.execute(task1);//ִ������1
        executor.execute(task2);//ִ������2
    }

    /*
     * ��������2
     */
    class Task1 implements Runnable{
        @Override
        public void run() {
            //���������ҵ��
            for(int i=0;i<1000;i++){
                System.out.println("hello xxx!!!");
            }
        }
    }

    /*
     * ��������2
     */
    class Task2 implements Runnable{
        @Override
        public void run() {
            //���������ҵ��
            for(int i=0;i<5;i++){
                System.out.println("hello world2!!!");
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutorTest test = new ThreadPoolExecutorTest();
        test.executeTask();
    }
}