package aaron.ren.pragram.ratelimiter.token;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TokensLimiter {

    // ���һ�����Ʒ���ʱ��
    public long timeStamp = System.currentTimeMillis();
    // Ͱ������
    public int capacity = 10;
    // ���������ٶ�10/s
    public int rate = 1000;
    // ��ǰ��������
    public int tokens;

    public void acquire(int permits ) {
            long now = System.currentTimeMillis();
            // ��ǰ������
            tokens = Math.min(capacity, (int) (tokens + (now - timeStamp) * rate / 1000));
            //ÿ��0.5�뷢���������������
            System.out.println("������������" + permits + "����ǰ��������" + tokens);
            timeStamp = now;
            if (tokens < permits) {
                // ����������,��ܾ�
                System.out.println("������");
            } else {
                // �������ƣ���ȡ����
                tokens = tokens -permits;
                System.out.println("ʣ������=" + tokens);
            }
    }

    public static void main(String[] args) throws InterruptedException {
        TokensLimiter tokensLimiter = new TokensLimiter();
        for(int i=1;i<100;i++){
            tokensLimiter.acquire(1);
            Thread.sleep(1000);
        }

    }

}