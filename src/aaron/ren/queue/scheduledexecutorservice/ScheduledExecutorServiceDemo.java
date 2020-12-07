package aaron.ren.queue.scheduledexecutorservice;

import java.sql.Connection;
import java.util.concurrent.*;

/**
 * 定时执行
 * 这个比Timer更加高级的实现
 *   高级在哪里呢？
 */
public class ScheduledExecutorServiceDemo {

    private static JVMBean jvmBean=new JVMBean();

    public static ScheduledExecutorService tableMetaExcutor = new ScheduledThreadPoolExecutor(1,
            new NamedThreadFactory("tableMetaChecker", 1, true));

    public static void main(String[] args) throws InterruptedException {
        tableMetaExcutor.scheduleAtFixedRate(() -> {
            System.out.println("=======begin start work=========");
            jvmBean=new JVMBean();

        }, 0, 6000L, TimeUnit.MILLISECONDS);


        new CountDownLatch(1).await();
    }


}
