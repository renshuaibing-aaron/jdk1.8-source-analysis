package aaron.ren.concurrentprogramming.interview;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBlockingQueue {

    private LinkedList<Integer>  queue;
    private ReentrantLock lock;

    //等待出队的条件
    private Condition  notEmpty;

    //等待入队的条件
    private Condition  notFull;

    private AtomicInteger size;

    private int capacity;

    //本质就是自定义实现一个线程安全的 有界的阻塞队列
    public BoundedBlockingQueue(int capacity) {
      this.capacity=capacity;
      lock=new ReentrantLock();
      notEmpty=lock.newCondition();
      notFull=lock.newCondition();
      size=new AtomicInteger(0);
        queue=new LinkedList<>();
    }

    public void enqueue(int element) throws InterruptedException {

        //入队的操作
        try{
            lock.lock();
            while(size.get()>=capacity){
                notFull.await();
            }
            queue.addFirst(element);
            size.incrementAndGet();
            notEmpty.signal();
        }finally {
            lock.unlock();

        }
    }

    public int dequeue() throws InterruptedException {

        //出队的操作
        try{
            lock.lock();
            while(size.get()==0){
                notEmpty.await();
            }
            Integer last = queue.getLast();
            queue.removeLast();
            size.decrementAndGet();
            notFull.signal();
            return  last;
        }finally {
            lock.unlock();
        }
    }

    public int size() {
        try{
            lock.lock();
            return size.get();
        }finally {
            lock.unlock();
        }
    }
}