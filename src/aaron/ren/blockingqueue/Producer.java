package aaron.ren.blockingqueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue��ʹ��
 * �����ߣ�������ʽ��put
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
                System.out.println("��Ҫ�Ž�ȥ��Ԫ���ǣ�"+element);
                blockingQueue.put(element++);
            }
        } catch (Exception e) {
            System.out.println("�������ڵȴ����пռ��ʱ�򱻴���ˣ�");
            e.printStackTrace();
        }
        System.out.println("�������Ѿ���ֹ���������̣�");
    }
}
