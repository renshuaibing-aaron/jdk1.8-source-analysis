package aaron.ren.pragram.ratelimiter.redisimpl;

import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RateLimiterRedisDemo {
    public static void main(String[] args) throws InterruptedException {
        // ���� RedissonClient ����
        RedissonClient client = Redisson.create();

        // ���� RRateLimiter ����
        RRateLimiter rateLimiter = client.getRateLimiter("myRateLimiter");
        // ��ʼ����������� = ÿ 1 ���Ӳ��� 2 ������
        rateLimiter.trySetRate(RateType.OVERALL, 2, 1, RateIntervalUnit.SECONDS);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 5; i++) {
            System.out.println(String.format("%s����������(%s)", simpleDateFormat.format(new Date()),
                    rateLimiter.tryAcquire()));
            Thread.sleep(250L);
        }
    }
}
