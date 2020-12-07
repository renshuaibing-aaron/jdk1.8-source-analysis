package aaron.ren.falseshare.disruptor;

import com.lmax.disruptor.RingBuffer;
import lombok.AllArgsConstructor;
import lombok.Data;


public class TEventProducer {

    public RingBuffer<OrderEvent> getRingBuffer() {
        return ringBuffer;
    }

    public void setRingBuffer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private RingBuffer<OrderEvent> ringBuffer;

    public TEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(long id) {
        //获取下一个可用序号
        long sequence = ringBuffer.next();

        try {
            //获取一个空对象（没有填充值）
            OrderEvent orderEvent = ringBuffer.get(sequence);

            //赋值
            orderEvent.setId(id);
        }finally {
            //提交
            ringBuffer.publish(sequence);
        }
    }
}