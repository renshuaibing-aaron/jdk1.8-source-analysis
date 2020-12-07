package aaron.ren.queue.delayqueue;

import java.util.concurrent.DelayQueue;

public class DelayqueueTest {
    public static void main(String[] args) throws InterruptedException {

        DelayQueue<DelayedEle> delayQueue = new DelayQueue<DelayedEle>();

        DelayedEle element1 = new DelayedEle(1000, "zlx");
        DelayedEle element2 = new DelayedEle(1000, "gh");

        delayQueue.offer(element1);
        delayQueue.offer(element2);

        element1 = delayQueue.take();
        System.out.println(element1);

    }

}
