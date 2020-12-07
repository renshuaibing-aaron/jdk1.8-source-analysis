package aaron.ren.pragram.ratelimiter.funnel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ©�������㷨
 */
public class FunnelRateLimiter {
    private Map<String, Funnel> funnelMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        FunnelRateLimiter limiter = new FunnelRateLimiter();
        int testAccessCount = 30;
        int capacity = 5;
        int allowQuota = 5;
        int perSecond = 30;
        int allowCount = 0;
        int denyCount = 0;
        for (int i = 0; i < testAccessCount; i++) {
            boolean isAllow = limiter.isActionAllowed("dadiyang", "doSomething", 5, 5, 30);
            if (isAllow) {
                allowCount++;
            } else {
                denyCount++;
            }
            System.out.println("����Ȩ�ޣ�" + isAllow);
            Thread.sleep(100);
        }
        System.out.println("���棺");
        System.out.println("©��������" + capacity);
        System.out.println("©���������ʣ�" + allowQuota + "��/" + perSecond + "��");

        System.out.println("���Դ���=" + testAccessCount);
        System.out.println("�������=" + allowCount);
        System.out.println("�ܾ�����=" + denyCount);
    }

    /**
     * ���ݸ�����©����������Ƿ��������
     *
     * @param username   �û���
     * @param action     ����
     * @param capacity   ©������
     * @param allowQuota ÿ������λʱ�����������
     * @param perSecond  ��λʱ�䣨�룩
     * @return �Ƿ��������
     */
    public boolean isActionAllowed(String username, String action, int capacity, int allowQuota, int perSecond) {
        String key = "funnel:" + action + ":" + username;
        if (!funnelMap.containsKey(key)) {
            funnelMap.put(key, new Funnel(capacity, allowQuota, perSecond));
        }
        Funnel funnel = funnelMap.get(key);
        return funnel.watering(1);
    }

    private static class Funnel {
        private int capacity;  //Ͱ������
        private float leakingRate; //Ͱ����������
        private int leftQuota; //Ͱ�ڵ�ʣ������
        private long leakingTs; //��һ�ε�����ʱ��

        public Funnel(int capacity, int count, int perSecond) {
            this.capacity = capacity;
            // ��Ϊ����ʹ�ú���Ϊ��λ��
            perSecond =perSecond* 1000;
            this.leakingRate = (float) count / perSecond;
        }

        /**
         * �����ϴ�ˮ������ʱ�䣬�ڳ��������Ŀռ�
         * ���ʾ���Ͱ�������������������ϴε�����ʱ��
         */
        private void makeSpace() {
            //��ȡ���ڵ�ʱ��
            long now = System.currentTimeMillis();
            long time = now - leakingTs;
            int leaked = (int) (time * leakingRate);
            if (leaked < 1) {
                return;
            }
            leftQuota = leftQuota+leaked;
            // ���ʣ�������������ʣ���������
            if (leftQuota > capacity) {
                leftQuota = capacity;
            }
            leakingTs = now;
        }

        /**
         * ©��©ˮ  �����ǲ��ǿ���ͨ��
         * @param quota ����
         * @return �Ƿ����㹻��ˮ�����������Ƿ�������ʣ�
         */
        public boolean watering(int quota) {
            makeSpace();
            int left = leftQuota - quota;
            if (left >= 0) {
                leftQuota = left;
                return true;
            }
            return false;
        }
    }
}

