package aaron.ren.falseshare.disruptor;

import com.lmax.disruptor.EventHandler;

public class TEventHandler implements EventHandler<OrderEvent> {

    /**
     * �¼���������--���������ѵ�����
     */
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        //�򵥴�ӡһ�µ�ǰ�¼�ID��ִ���̵߳�����
        System.out.println(event.getId() + " " +Thread.currentThread().getName());
    }

}