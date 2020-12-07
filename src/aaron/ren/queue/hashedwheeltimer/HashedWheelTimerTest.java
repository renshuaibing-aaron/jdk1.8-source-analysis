package aaron.ren.queue.hashedwheeltimer;

import io.netty.util.HashedWheelTimer;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 利用这个可以实现延时队列的设计
 *  具体源码可以查看netty源码
 */
public class HashedWheelTimerTest {

    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);

        System.out.println("start:" + LocalDateTime.now().format(formatter));

        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("task :" + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);
        new CountDownLatch(1).await();
    }



    @Test
    public void test2() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS);

        System.out.println("start:" + LocalDateTime.now().format(formatter));

        hashedWheelTimer.newTimeout(timeout -> {
            Thread.sleep(3000);
            System.out.println("task1:" + LocalDateTime.now().format(formatter));
        }, 3, TimeUnit.SECONDS);

        hashedWheelTimer.newTimeout(timeout -> System.out.println("task2:" + LocalDateTime.now().format(
                formatter)), 4, TimeUnit.SECONDS);

        Thread.sleep(10000);
    }
}
