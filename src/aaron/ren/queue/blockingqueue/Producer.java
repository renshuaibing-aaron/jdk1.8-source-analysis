package aaron.ren.queue.blockingqueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue的使用
 * 生产者，生产方式：put
 * @author brucexiajun
 *
 */

public class Producer implements Runnable {

    private  BlockingQueue<Integer> blockingQueue;
    private static int element = 0;

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    public void run() {
        try {
            while(element < 20) {
                System.out.println("将要放进去的元素是："+element);
                blockingQueue.put(element++);
            }
        } catch (Exception e) {
            System.out.println("生产者在等待空闲空间的时候被打断了！");
            e.printStackTrace();
        }
        System.out.println("生产者已经终止了生产过程！");
    }
}
