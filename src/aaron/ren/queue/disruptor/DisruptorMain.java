package aaron.ren.queue.disruptor;
/**
 * @description disruptor����������ÿ10ms��disruptor�в���һ��Ԫ�أ������߶�ȡ���ݣ�����ӡ���ն�
 */
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;


public class DisruptorMain
{
    public static void main(String[] args) throws Exception
    {
        // �����е�Ԫ��
        class Element {

            private int value;

            public int get(){
                return value;
            }

            public void set(int value){
                this.value= value;
            }

        }

        // �����ߵ��̹߳���
        ThreadFactory threadFactory = new ThreadFactory(){
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "simpleThread");
            }
        };

        // RingBuffer��������,��ʼ��RingBuffer��ʱ��ʹ��
        EventFactory<Element> factory = new EventFactory<Element>() {
            @Override
            public Element newInstance() {
                return new Element();
            }
        };

        // ����Event��handler
        EventHandler<Element> handler = new EventHandler<Element>(){
            @Override
            public void onEvent(Element element, long sequence, boolean endOfBatch)
            {
                System.out.println("Element: " + element.get());
            }
        };

        // ��������
        BlockingWaitStrategy strategy = new BlockingWaitStrategy();

        // ָ��RingBuffer�Ĵ�С
        int bufferSize = 16;

        // ����disruptor�����õ�������ģʽ
        Disruptor<Element> disruptor = new Disruptor(factory, bufferSize, threadFactory, ProducerType.SINGLE, strategy);

        // ����EventHandler
        disruptor.handleEventsWith(handler);

        // ����disruptor���߳�
        disruptor.start();

        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

        for (int l = 0; true; l++)
        {
            // ��ȡ��һ������λ�õ��±�
            long sequence = ringBuffer.next();
            try
            {
                // ���ؿ���λ�õ�Ԫ��
                Element event = ringBuffer.get(sequence);
                // ���ø�λ��Ԫ�ص�ֵ
                event.set(l);
            }
            finally
            {
                ringBuffer.publish(sequence);
            }
            Thread.sleep(10);
        }
    }
}