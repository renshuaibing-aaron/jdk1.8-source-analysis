package aaron.ren.concurrentprogramming.conditiondemo;

/**
 * @author renshuaibing
 * @version 1.0
 * @description: TODO
 * @date 2020/11/23 19:12
 */

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue {

    private LinkedList<Object> buffer;    //����������
    private int maxSize ;           //�������ֵ�Ƕ���
    private Lock lock;
    private Condition fullCondition;
    private Condition notFullCondition;
    BoundedQueue(int maxSize){
        this.maxSize = maxSize;
        buffer = new LinkedList<Object>();
        lock = new ReentrantLock();
        fullCondition = lock.newCondition();
        notFullCondition = lock.newCondition();
    }

    /**
     * ������
     * @param obj
     * @throws InterruptedException
     */
    public void put(Object obj) throws InterruptedException {
        lock.lock();    //��ȡ��
        try {
            while (maxSize == buffer.size()){
                notFullCondition.await();       //���ˣ���ӵ��߳̽���ȴ�״̬
            }
            buffer.add(obj);
            fullCondition.signal(); //֪ͨ
        } finally {
            lock.unlock();
        }
    }

    /**
     * ������
     * @return
     * @throws InterruptedException
     */
    public Object get() throws InterruptedException {
        Object obj;
        lock.lock();
        try {
            while (buffer.size() == 0){ //������û�������� �߳̽���ȴ�״̬
                fullCondition.await();
            }
            obj = buffer.poll();
            notFullCondition.signal(); //֪ͨ
        } finally {
            lock.unlock();
        }
        return obj;
    }

}
