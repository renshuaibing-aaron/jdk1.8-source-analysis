package aaron.ren.queue.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时执行任务
 *   这个有个很大的缺陷是 定时任务的执行 这里用的是单线程  效率太低
 */
public class TimerDemo {
    public static void main(String[] args) {
        Timer timer = new Timer(); // 1. 创建Timer实例，关联线程不能是daemon(守护/后台)线程
        FooTimerTask fooTimerTask = new FooTimerTask(); // 2. 创建任务对象
        timer.schedule(fooTimerTask, 3000L, 2000L); // 3. 通过Timer定时定频率调用fooTimerTask的业务代码
    }


    static class FooTimerTask extends TimerTask {
        @Override
        public void run() {
            // TODO 业务代码......
            System.out.println("Hello Timer!");
        }
    }
}
