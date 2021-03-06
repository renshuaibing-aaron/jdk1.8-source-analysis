package aaron.ren.concurrentprogramming.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("=============");
            }
        };
        executorService.execute(runnable);
      //  Future<?> submit = executorService.submit(runnable);
      //  submit.get();
    }
}

