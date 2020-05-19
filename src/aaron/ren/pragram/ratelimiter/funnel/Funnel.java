package aaron.ren.pragram.ratelimiter.funnel;

/**
 *©���㷨
 *
 * @author hling
 */
public class Funnel {

    private static final long NANO = 1000000000;

    private long capacity;
    private long leftQuota;
    private long leakingTs;
    private int rate;

    /**
     * ���캯��
     *
     * @param capatity ����
     * @param rate ÿ��©ˮ����
     */
    public Funnel(int capatity, int rate) {
        this.capacity = capatity;
        this.leakingTs = System.nanoTime();
        this.rate = rate;
    }

    /**
     * ��ˮ
     */
    private void makeSpace() {
        long now = System.nanoTime();
        long time = now - leakingTs;
        long leaked = time * rate / NANO;
        if (leaked < 1) {
            return;
        }
        leftQuota += leaked;

        if (leftQuota > capacity) {
            leftQuota = capacity;
        }
        leakingTs = now;
    }

    /**
     * ©ˮ��Ͱ��ˮ�������ͷ���false
     * @param quota ©ˮ��
     * @return �Ƿ�©ˮ�ɹ�
     */
    public boolean tryWatering(int quota) {
        makeSpace();
        long left = leftQuota - quota;
        if (left >= 0) {
            leftQuota = left;
            return true;
        }
        return false;
    }

    /**
     * ©ˮ��ûˮ������ֱ�������㹻��ˮ
     * @param quota Ҫ©������
     */
    public void watering(int quota) {
        long left;
        try {
            do {
                makeSpace();
                left = leftQuota - quota;
                if (left >= 0) {
                    leftQuota = left;
                } else {
                    Thread.sleep(1);
                }
            } while (left < 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
