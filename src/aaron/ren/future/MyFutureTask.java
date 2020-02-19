package aaron.ren.future;


import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MyFutureTask<V> implements Future<V>,Runnable {
    public Callable<V> callable; //ҵ���߼�
    boolean running = false ,done = false,cancel = false;// ҵ���߼�ִ��״̬
    ReentrantLock lock ;//��
    V outcome;//���

    public MyFutureTask(Callable<V> callable) {
        if(callable == null) {
            throw new NullPointerException("callable cannot be null!");
        }
        this.callable = callable;
        this.done = false;
        this.lock = new ReentrantLock();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        callable = null;
        cancel = true;
        return true;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        try {
            this.lock.lock();//�Ȼ�ȡ������ú�˵��ҵ���߼��Ѿ�ִ�����
            return outcome;
        }finally{
            this.lock.unlock();
        }
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        try {
            this.lock.tryLock(timeout, unit);
            return outcome;
        }catch (InterruptedException e) {
            return null;
        }finally{
            this.lock.unlock();
        }
    }
    @Override
    public void run() {
        try {
            this.lock.lock(); // �����̣߳�����������ֹgetʱֱ�ӷ���
            running = true;
            try {
                outcome = callable.call(); // ҵ���߼�
            } catch (Exception e) {
                e.printStackTrace();
            }
            done = true;
            running = false;
        }finally {
            this.lock.unlock(); // ������get�ɻ�ȡ
        }
    }
}
