package aaron.ren.falseshare.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TMainDisruptor {
    public static void main(String[] args) throws InterruptedException {

        //创建生产者工厂
        TEventFactory eventFactory = new TEventFactory();
        int ringbuffersize = 1024 * 1024;
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        /**
         * 实例化一个 Disruptor，Disruptor 本身并不做为生产者或是消费者，它更多像是一个包装器，将真正核心的生产者、消费者以及生产消费的动作以及容器串起来
         *
         * 1、消息工厂对象
         * 2、容器的长度
         * 3、线程池
         * 4、生产者模式
         * 5、等待策略
         */

        Disruptor<OrderEvent> disruptor = new Disruptor(eventFactory, ringbuffersize, executorService, ProducerType.SINGLE,
                new BlockingWaitStrategy());

        //这里定义了俩个相同事件
        TEventHandler t1 = new TEventHandler();
        TEventHandler t2 = new TEventHandler();

        //跟消费者建立关系--监听
        disruptor.handleEventsWith(t1);
        //顾名思义：执行完t1后执行t2。（对同一个任务线性执行）
        disruptor.after(t1).handleEventsWith(t2);

        //启动
        disruptor.start();

        //数据存储工具
        RingBuffer ringBuffer = disruptor.getRingBuffer();

        //创建生产者
        TEventProducer producer = new TEventProducer(ringBuffer);

        //投递数据
        for(long i=0 ;i<10000;i++) {
            producer.sendData(i);
        }

       // executorService.shutdown();
        //disruptor.shutdown();
    }

}