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
        System.out.println("线程"+(index+1)+" 数完100，将暂停任务，等待指挥部的下一步通知。");
        try {
            barrier.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("线程"+(index+1)+" 收到解除await的通知，继续开始下一步操作。");
    }

    public static void main(String[] args) {
        int count = 4;
        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("指挥部：所有人都已完成数100的任务，可以继续各自做剩余任务了。");
            }
        };
        CyclicBarrier barrier = new CyclicBarrier(count, runnable);
        for(int i = 0 ; i < count ; i++){
            CyclicBarrierTest test = new CyclicBarrierTest(barrier,i);
            test.start();
        }
    }

}