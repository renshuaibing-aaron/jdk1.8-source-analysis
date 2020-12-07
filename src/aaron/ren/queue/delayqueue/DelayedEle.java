package aaron.ren.queue.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedEle implements Delayed {

    private final long delayTime; //�ӳ�ʱ��
    private final long expire;  //����ʱ��
    private String data;   //����

    public DelayedEle(long delay, String data) {
        delayTime = delay;
        this.data = data;
        expire = System.currentTimeMillis() + delay;
    }

    /**
     * ʣ��ʱ��=����ʱ��-��ǰʱ��
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
    }

    /**
     * ���ȶ����������ȼ�����
     *
     */
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) -o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DelayedElement{");
        sb.append("delay=").append(delayTime);
        sb.append(", expire=").append(expire);
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }


}