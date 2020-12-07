package aaron.ren.concurrentprogramming.interview;

import org.redisson.pubsub.SemaphorePubSub;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;

    //注意使用Semaphore 控制线程的执行顺序
    private Semaphore semaphoreB;
    private Semaphore semaphoreA;
    private Semaphore semaphoreC;
    private Semaphore semaphoreD;
    public FizzBuzz(int n) {
        this.n = n;
        semaphoreA=new Semaphore(1);
        semaphoreB=new Semaphore(0);
        semaphoreC=new Semaphore(0);
        semaphoreD=new Semaphore(0);
    }

    // printFizz.run() outputs "fizz".
    //被 3 整除，输出 "fizz"
    public void fizz(Runnable printFizz) throws InterruptedException {
        while(true){
            semaphoreB.acquire();
            printFizz.run();
            semaphoreA.release();
        }

    }

    // printBuzz.run() outputs "buzz".
    //被 5 整除，输出 "buzz"
    public void buzz(Runnable printBuzz) throws InterruptedException {

        while (true){
            semaphoreC.acquire();
            printBuzz.run();
            semaphoreA.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    //同时被 3 和 5 整除，输出 "fizzbuzz"
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

        while(true){
            semaphoreD.acquire();
            printFizzBuzz.run();
            semaphoreA.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    //这个没有控制顺序啊
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i++){
           semaphoreA.acquire();
            if(i%3==0&&i%5!=0){
                semaphoreB.release();
            }else if(i%3!=0&&i%5==0){
                semaphoreC.release();
            }else if(i%3==0&&i%5==0){
                semaphoreD.release();
            }else{

                printNumber.accept(i);
                semaphoreA.release();
            }
        }
    }

    private static class PrintFizz implements Runnable {
        @Override
        public void run() {
            System.out.println("fizz");
        }
    }

    private static class PrintBuzz implements Runnable {
        @Override
        public void run() {
            System.out.println("buzz");
        }
    }

    private static class PrintFizzBuzz implements Runnable {
        @Override
        public void run() {
            System.out.println("fizzbuzz");
        }
    }


    public static void main(String[] args) {
        FizzBuzz fizzBuzz=new FizzBuzz(15);

        Thread threadA=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizz(new PrintFizz());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadB=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.buzz(new PrintBuzz());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadC=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizzbuzz(new PrintFizzBuzz() );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadD=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.number(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.println(value);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}