package aaron.ren.concurrentprogramming.interview;

import java.util.concurrent.Semaphore;

public class Foo {

    //∞¥’’À≥–Ú¥Ú”°
    private Semaphore  semaphoreA;
    private Semaphore  semaphoreB;
    private Semaphore  semaphoreC;

    public Foo() {
        semaphoreA=new Semaphore(1);
        semaphoreB=new Semaphore(0);
        semaphoreC=new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        semaphoreA.acquire();
        printFirst.run();
        semaphoreB.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        semaphoreB.acquire();
        printSecond.run();
        semaphoreC.release();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        semaphoreC.acquire();
        printThird.run();
        semaphoreA.release();
    }
}
