package aaron.ren.concurrentprogramming.interview;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBlockingQueue {

    private LinkedList<Integer>  queue;
    private ReentrantLock lock;

    //�ȴ����ӵ�����
    private Condition  notEmpty;

    //�ȴ���ӵ�����
    private Condition  notFull;

    private AtomicInteger size;

    private int capacity;

    //���ʾ����Զ���ʵ��һ���̰߳�ȫ�� �н����������
    public BoundedBlockingQueue(int capacity) {
      this.capacity=capacity;
      lock=new ReentrantLock();
      notEmpty=lock.newCondition();
      notFull=lock.newCondition();
      size=new AtomicInteger(0);
        queue=new LinkedList<>();
    }

    public void enqueue(int element) throws InterruptedException {

        //��ӵĲ���
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

        //���ӵĲ���
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