package aaron.ren.concurrentprogramming.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author renshuaibing
 */
public class CyclicBarrierTest  extends Thread{

    private CyclicBarrier barrier;
    private int index;

    public CyclicBarrierTest(CyclicBarrier barrier,int index){
        this.barrier = barrier;
        this.index = index;
    }

    @Override
    public void run(){
        for(int i = 0 ; i < 100 ; i++){

        }
        System.out.println("�߳�"+(index+1)+" ����100������ͣ���񣬵ȴ�ָ�Ӳ�����һ��֪ͨ��");
        try {
            barrier.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("�߳�"+(index+1)+" �յ����await��֪ͨ��������ʼ��һ��������");
    }

    public static void main(String[] args) {
        int count = 4;
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("ָ�Ӳ��������˶��������100�����񣬿��Լ���������ʣ�������ˡ�");
            }
        };
        CyclicBarrier barrier = new CyclicBarrier(count, runnable);
        for(int i = 0 ; i < count ; i++){
            CyclicBarrierTest test = new CyclicBarrierTest(barrier,i);
            test.start();
        }
    }

}