package aaron.ren.pragram.ratelimiter.funnel;

/**
 *漏斗算法
 *
 * @author hling
 */
public class Funnel {

    private static final long NANO = 1000000000;

    private long capacity;  //桶的容量
    private long leftQuota;//当前桶内剩余的请求数
    private long leakingTs; //上一次请求的时间
    private int rate;  //流出的速率 注意这个和令牌桶的区别

    /**
     * 构造函数
     *
     * @param capatity 容量
     * @param rate 每秒漏水数量
     */
    public Funnel(int capatity, int rate) {
        this.capacity = capatity;
        this.leakingTs = System.nanoTime();
        this.rate = rate;
    }

    /**
     * 补水
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
     * 漏水。桶里水量不够就返回false
     * @param quota 漏水量
     * @return 是否漏水成功
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
     * 漏水。没水就阻塞直到蓄满足够的水
     * @param quota 要漏的数量
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
