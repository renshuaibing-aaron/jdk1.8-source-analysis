package aaron.ren.queue.scheduledexecutorservice;

import java.sql.Connection;
import java.util.concurrent.*;

/**
 * ��ʱִ��
 * �����Timer���Ӹ߼���ʵ��
 *   �߼��������أ�
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
