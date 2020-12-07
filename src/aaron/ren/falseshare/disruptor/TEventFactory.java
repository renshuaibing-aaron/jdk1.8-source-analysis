package aaron.ren.falseshare.disruptor;

import com.lmax.disruptor.EventFactory;

public class TEventFactory implements EventFactory<OrderEvent> {
    @Override
    public OrderEvent newInstance() {

        //实例化数据（建好空数据，等后面取的时候可以直接用）
        return new OrderEvent();
    }
}