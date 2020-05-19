package aaron.ren.pragram.ratelimiter.funnel;

import aaron.ren.pragram.ratelimiter.funnel.Funnel;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Sender {
    /** ���� */
    private static final long NANO = 1000000000;

    private volatile static long totalCount = 0;
    private volatile static long totalSize = 0;

    private final static int MAX_PACKET_SIZE_KB = 100; // ÿ�����ݰ�50KB
    private final static int THRESHOLD_MB = 50; // ÿ��50MB����

    public static final void main(String[] args) {
        startLogger();

        // �������ݰ�
        Random rand = new Random();
        // ����©Ͱ����������Ϊ3��ˮ�Σ����ݰ�����С�� Ӧ����ʵ���������������С��1�������൱��Ͱ��������һ��ˮ��������Ȼ���޷�©ˮ��������Ϊ0
        // ����ԽС��������΢��ʱ��Ƭ�ڵ�����Խ���ᳬ���������ƣ�����ˮ�δ�С�仯�ϴ�ʣ��ˮ���պò���һ��ˮ
        // ������£������׽���ȴ���ˮ��״̬�������ٶȵ���Ԥ��ֵ��
        // ����Խ�󣬺���ٶ�Խ�ȶ��ӽ��趨ֵ����ϸ����ʱ�����ٶȲ�����Χ���ܱȽϴ󣬶�ʱ����©���ˣ����٣�Ҳ����
        Funnel funnel = new Funnel(3 * MAX_PACKET_SIZE_KB * 1024, THRESHOLD_MB * 1024 * 1024);
        do {
            byte[] data = new byte[rand.nextInt(100) * 1024];
            funnel.watering(data.length);

            totalCount++;
            totalSize += data.length;
        } while (true);
    }

    /**
     * ����ͳ���߳�
     */
    private static void startLogger() {
        Timer logTimer = new Timer(true);
        logTimer.schedule(new TimerTask() {
            private long lastTs = 0;
            private long lastCount = 0;
            private long lastSize = 0;

            @Override
            public void run() {
                // ��ӡ����ƽ���ٶ�
                if (lastTs == 0) {
                    // ������һ��
                    lastTs = System.nanoTime();
                    return;
                }

                long tempCount = totalCount;
                long tempSize = totalSize;
                long tempTs = System.nanoTime();

                long count = tempCount - lastCount;
                long size = tempSize - lastSize;
                long duration = tempTs - lastTs;
                float byteSpeed = (float) size * NANO / duration / (1024 * 1024);

                lastCount = tempCount;
                lastSize = tempSize;
                lastTs = tempTs;

                String log = String.format("���%d�빲����%d������%fMB��ƽ���ٶȣ�%dMB/��", (int) (1.0f * duration / NANO + 0.5), count,
                        (float) size / (1024 * 1024), Math.round(byteSpeed));
                System.out.println(log);
            }
        }, new Date(), 5000);
    }

}

