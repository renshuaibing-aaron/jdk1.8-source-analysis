package aaron.ren.falseshare.disruptor;

import com.lmax.disruptor.EventFactory;

public class TEventFactory implements EventFactory<OrderEvent> {
    @Override
    public OrderEvent newInstance() {

        //ʵ�������ݣ����ÿ����ݣ��Ⱥ���ȡ��ʱ�����ֱ���ã�
        return new OrderEvent();
    }
}