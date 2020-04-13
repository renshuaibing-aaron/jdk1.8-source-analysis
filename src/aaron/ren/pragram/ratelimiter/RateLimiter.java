package aaron.ren.pragram.ratelimiter;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;

/**
 * @author xiezhengchao
 * @since 18/1/3 ����9:45.
 * ������
 */
public class RateLimiter{

    private volatile int token;
    private final int originToken;

    private static Unsafe unsafe = null;
    private static final long valueOffset;
    private final Object lock = new Object();

    static {
        try {
            // Ӧ�ÿ�����ʹ��unsafe�������ͨ�������ȡ
            Class<?> clazz = Unsafe.class;
            Field f = clazz.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(clazz);
            valueOffset = unsafe.objectFieldOffset(RateLimiter.class.getDeclaredField("token"));
        } catch (Exception ex) {throw new Error(ex);}
    }

    public RateLimiter(int token){
        this.originToken = token;
        this.token = token;
    }

    /**
     * ��ȡһ������
     */
    public boolean acquire(){
        int current = token;
        if(current<=0){
            // ��֤��token�Ѿ��ù���������Ȼ�л�ȡ����������
            current = originToken;
        }

        long expect = 1000;// max wait 1s
        long future = System.currentTimeMillis()+expect;
        while(current>0){
            if(compareAndSet(current, current-1)){
                return true;
            }
            current = token;
            if(current<=0 && expect>0){
                // ����Ч���ڵȴ�֪ͨ
                synchronized (lock){
                    try {
                        lock.wait(expect);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                current = token;
                if(current<=0){
                    current = originToken;
                }
                expect = future - System.currentTimeMillis();
            }
        }
        return false;
    }

    private boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    /**
     * ��������
     */
    public void supplement(final ExecutorService executorService){
        this.token = originToken;
        executorService.execute(() -> {
            synchronized (lock){
                lock.notifyAll();
            }
        });
    }

}