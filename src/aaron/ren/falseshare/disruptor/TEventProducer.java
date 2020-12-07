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
        //��ȡ��һ���������
        long sequence = ringBuffer.next();

        try {
            //��ȡһ���ն���û�����ֵ��
            OrderEvent orderEvent = ringBuffer.get(sequence);

            //��ֵ
            orderEvent.setId(id);
        }finally {
            //�ύ
            ringBuffer.publish(sequence);
        }
    }
}