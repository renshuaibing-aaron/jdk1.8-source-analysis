package aaron.ren.concurrentprogramming.whilenotify;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {

    private AtomicReference<Thread> sign =new AtomicReference<>();

    public void lock() { // <1>
        Thread current = Thread.currentThread();
        while(!sign .compareAndSet(null, current)) {
            // <1.1>  ÈÃ³öCPU  ×ÔÐý
            Thread.yield();
        }
    }

    public void unlock () { // <2>
        Thread current = Thread.currentThread();
        sign .compareAndSet(current, null);
    }

}