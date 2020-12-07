package aaron.ren.pragram.ratelimiter.countnum;

/**
 * 计数器方法
 * 计数器方法 有个问题就是在1000毫秒的时间窗口里面加入前999毫秒请求是0 最后1毫秒请求的数量是
 * 100 请求通不过 这样计数器的方法就失去了本来的意义
 */
public class CounterTest {
    public long timeStamp = getNowTime();
    public int reqCount = 0; // 当前时间窗口的请求数量
    public final int limit = 100; // 时间窗口内最大请求数
    public final long interval = 1000; // 时间窗口ms

    public boolean grant() {
        long now = getNowTime();
        if (now < timeStamp + interval) {
            // 在时间窗口内
            reqCount++;
            // 判断当前时间窗口内是否超过最大请求控制数
            return reqCount <= limit;
        } else {
            timeStamp = now;
            // 超时后重置
            reqCount = 1;
            return true;
        }
    }

    public long getNowTime() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        CounterTest counter=new CounterTest();

        for(int i=1;i<1000;i++){
            boolean grant = counter.grant();
            System.out.println(grant);
        }


    }
}