package aaron.ren.concurrentprogramming.interview;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;

    private Semaphore semaphorezero;
    private Semaphore semaphoreeven;
    private Semaphore semaphoreodd;

    public ZeroEvenOdd(int n) {
        this.n = n;
        semaphorezero=new Semaphore(1);
        semaphoreeven=new Semaphore(0);
        semaphoreodd=new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {

        for(int i=1;i<=n;i++){

            semaphorezero.acquire();
            printNumber.accept(0);
            if(i%2==0){
semaphoreeven.release();

            }else{
                semaphoreodd.release();
            }
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {

        for(int i=2;i<=n;i+=2){
            semaphoreeven.acquire();
            printNumber.accept(i);
            semaphoreodd.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i+=2){
            semaphoreodd.acquire();
            printNumber.accept(i);
            semaphorezero.release();
        }
    }
}