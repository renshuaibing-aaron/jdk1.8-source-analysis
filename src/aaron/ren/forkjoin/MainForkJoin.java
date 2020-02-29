package aaron.ren.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class MainForkJoin {


    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        Integer sum = forkJoinPool.invoke(new CountRecursiveTask(1, 100));
        System.out.println("=================" + sum);
    }


}
