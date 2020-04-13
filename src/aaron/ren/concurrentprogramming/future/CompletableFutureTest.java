package aaron.ren.concurrentprogramming.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class CompletableFutureTest {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        final int evenNumber = 2 + 4 + 6 + 8 + 10;

        CompletableFuture<Integer> oddNumber = CompletableFuture.supplyAsync(new OddNumberPlus());

        try {
            Thread.sleep(1000);
            System.out.println("0.��ʼ�ˣ�"+ (System.currentTimeMillis()-startTime) +"��");
            //�����ʵ�ֻص�
            oddNumber.thenAccept(oddNumberResult->
            {
                System.out.println("1.��ʼ�ˣ�"+ (System.currentTimeMillis()-startTime) +"��");
                System.out.println("��ʱ������Ϊ��"+(evenNumber+oddNumberResult));
            });
            oddNumber.get();
        } catch (Exception e) {
            System.out.println(e);
        }


        new CountDownLatch(1).await();
    }
}