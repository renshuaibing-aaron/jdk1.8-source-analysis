package aaron.ren.pragram.ratelimiter.token;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TokensLimiter {

    // 最后一次令牌发放时间
    public long timeStamp = System.currentTimeMillis();
    // 桶的容量
    public int capacity = 10;
    // 令牌生成速度10/s
    public int rate = 1000;
    // 当前令牌数量
    public int tokens;

    public void acquire(int permits ) {
            long now = System.currentTimeMillis();
            // 当前令牌数
            tokens = Math.min(capacity, (int) (tokens + (now - timeStamp) * rate / 1000));
            //每隔0.5秒发送随机数量的请求
            System.out.println("请求令牌数：" + permits + "，当前令牌数：" + tokens);
            timeStamp = now;
            if (tokens < permits) {
                // 若不到令牌,则拒绝
                System.out.println("限流了");
            } else {
                // 还有令牌，领取令牌
                tokens = tokens -permits;
                System.out.println("剩余令牌=" + tokens);
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