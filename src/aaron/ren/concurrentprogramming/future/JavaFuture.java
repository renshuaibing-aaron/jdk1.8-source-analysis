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
        new Thread(future).start();//�����߳�ȥִ��
        return future; // ���̷���future����
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
        new Thread(future).start();// ���ύ���̳߳���
        return future;
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        JavaFuture jf = new JavaFuture();
        Future<String> future = jf.mySearch("NettyȨ��ָ��");// ����future
        System.out.println("Begin search,get future!");

        // ����1-����ȡ������ȴ�3s��᷵��
        String prods = future.get();//��ȡprods
        System.out.println("get result:"+prods);

        // ����2-��ȡ������1s��ȡ������
        Thread.sleep(1000);
        future.cancel(false);//trueʱ���ж��̣߳�false����
        System.out.println("Future is canceled? " + (future.isCancelled()?"yes":"no"));

        Thread.sleep(4000); //�ȴ�4s���һ��future�����߳��Ƿ���ִ��
    }

}






