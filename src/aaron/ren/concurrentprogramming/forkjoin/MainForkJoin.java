package aaron.ren.concurrentprogramming.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class MainForkJoin {


    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();

        ForkJoinPool forkJoinPool = new ForkJoinPool(i);

        Integer sum = forkJoinPool.invoke(new CountRecursiveTask(1, 100));
        System.out.println("=================" + sum);
    }


}
