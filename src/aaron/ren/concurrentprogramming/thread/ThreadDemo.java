package aaron.ren.concurrentprogramming.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {

            ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1));
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    // 此处仅使用示例代码
                    System.out.println("do something async...");
                }
            });

        }
    }
}
