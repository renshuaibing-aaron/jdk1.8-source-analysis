package aaron.ren.concurrentprogramming.interview;

import java.util.concurrent.Semaphore;

public class FooBar {

    private Semaphore semaphoreA;
    private Semaphore semaphoreB;
    private int n;

    public FooBar(int n) {
        this.n = n;
        semaphoreA=new Semaphore(1);
        semaphoreB=new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            semaphoreA.acquire();
            printFoo.run();
            semaphoreB.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            semaphoreB.acquire();
            printBar.run();
            semaphoreA.release();
        }
    }
}

