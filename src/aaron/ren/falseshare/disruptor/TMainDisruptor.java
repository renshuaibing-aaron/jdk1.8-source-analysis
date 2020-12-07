package aaron.ren.falseshare.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TMainDisruptor {
    public static void main(String[] args) throws InterruptedException {

        //���������߹���
        TEventFactory eventFactory = new TEventFactory();
        int ringbuffersize = 1024 * 1024;
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        /**
         * ʵ����һ�� Disruptor��Disruptor ��������Ϊ�����߻��������ߣ�����������һ����װ�������������ĵ������ߡ��������Լ��������ѵĶ����Լ�����������
         *
         * 1����Ϣ��������
         * 2�������ĳ���
         * 3���̳߳�
         * 4��������ģʽ
         * 5���ȴ�����
         */

        Disruptor<OrderEvent> disruptor = new Disruptor(eventFactory, ringbuffersize, executorService, ProducerType.SINGLE,
                new BlockingWaitStrategy());

        //���ﶨ����������ͬ�¼�
        TEventHandler t1 = new TEventHandler();
        TEventHandler t2 = new TEventHandler();

        //�������߽�����ϵ--����
        disruptor.handleEventsWith(t1);
        //����˼�壺ִ����t1��ִ��t2������ͬһ����������ִ�У�
        disruptor.after(t1).handleEventsWith(t2);

        //����
        disruptor.start();

        //���ݴ洢����
        RingBuffer ringBuffer = disruptor.getRingBuffer();

        //����������
        TEventProducer producer = new TEventProducer(ringBuffer);

        //Ͷ������
        for(long i=0 ;i<10000;i++) {
            producer.sendData(i);
        }

       // executorService.shutdown();
        //disruptor.shutdown();
    }

}