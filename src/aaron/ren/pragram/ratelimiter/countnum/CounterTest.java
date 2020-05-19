package aaron.ren.pragram.ratelimiter.countnum;

/**
 * ����������
 */
public class CounterTest {
    public long timeStamp = getNowTime();
    public int reqCount = 0;
    public final int limit = 100; // ʱ�䴰�������������
    public final long interval = 1000; // ʱ�䴰��ms

    public boolean grant() {
        long now = getNowTime();
        if (now < timeStamp + interval) {
            // ��ʱ�䴰����
            reqCount++;
            // �жϵ�ǰʱ�䴰�����Ƿ񳬹�������������
            return reqCount <= limit;
        } else {
            timeStamp = now;
            // ��ʱ������
            reqCount = 1;
            return true;
        }
    }

    public long getNowTime() {
        return System.currentTimeMillis();
    }
}