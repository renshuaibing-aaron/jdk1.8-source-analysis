package aaron.ren.falseshare.disruptor;

import com.lmax.disruptor.EventHandler;

public class TEventHandler implements EventHandler<OrderEvent> {

    /**
     * 事件驱动监听--消费者消费的主体
     */
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        //简单打印一下当前事件ID和执行线程的名称
        System.out.println(event.getId() + " " +Thread.currentThread().getName());
    }

}