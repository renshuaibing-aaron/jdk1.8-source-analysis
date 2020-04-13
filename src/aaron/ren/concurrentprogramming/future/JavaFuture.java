package aaron.ren.concurrentprogramming.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class JavaFuture {

    public Future<String> search(String prodName) {
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call()  {
                try {
                    System.out.println(String.format("	>>search price of %s from internet!",prodName));
                    Thread.sleep(3000);
                    return "$99.99";
                }catch(InterruptedException e){
                    System.out.println("search function is Interrupted!");
                }
                return null;
            }
        });
        new Thread(future).start();//交给线程去执行
        return future; // 立刻返回future对象
    }

    public Future<String> mySearch(String prodName) {
        MyFutureTask<String> future = new MyFutureTask<String>(new Callable<String>() {
            @Override
            public String call()  {
                try {
                    System.out.println(String.format("	>>search price of %s from internet!",prodName));
                    Thread.sleep(3000);
                    return "$99.99";
                }catch(InterruptedException e){
                    System.out.println("search function is Interrupted!");
                }
                return null;
            }
        });
        new Thread(future).start();// 或提交到线程池中
        return future;
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        JavaFuture jf = new JavaFuture();
        Future<String> future = jf.mySearch("Netty权威指南");// 返回future
        System.out.println("Begin search,get future!");

        // 测试1-【获取结果】等待3s后会返回
        String prods = future.get();//获取prods
        System.out.println("get result:"+prods);

        // 测试2-【取消任务】1s后取消任务
        Thread.sleep(1000);
        future.cancel(false);//true时会中断线程，false不会
        System.out.println("Future is canceled? " + (future.isCancelled()?"yes":"no"));

        Thread.sleep(4000); //等待4s检查一下future所在线程是否还在执行
    }

}






