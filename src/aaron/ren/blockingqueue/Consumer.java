package aaron.ren.blockingqueue;


import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private  BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    public void run() {
        try {
            while(true) {
                System.out.println("ȡ������Ԫ���ǣ�"+blockingQueue.take());
            }
        } catch (Exception e) {
            System.out.println("�������ڵȴ��²�Ʒ��ʱ�򱻴���ˣ�");
            e.printStackTrace();
        }
    }
}
