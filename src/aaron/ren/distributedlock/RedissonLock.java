package aaron.ren.distributedlock;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonLock {

    public static void main(String[] args)  {
        String lockKey = "lock";
        // 1.构造redisson实现分布式锁必要的Config
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:5379").setPassword("123456").setDatabase(0);
        // 2.构造RedissonClient
        RedissonClient redissonClient = Redisson.create(config);
        // 3.获取锁对象实例（无法保证是按线程的顺序获取到）
        RLock rLock = redissonClient.getLock(lockKey);
        //模拟100个线程抢锁
        for (int i = 0; i < 100; i++) {
            new Thread(new TestThread(i, rLock)).start();
        }
    }

    static class TestThread implements Runnable {
        private Integer threadFlag;
        private RLock lock;

        public TestThread(Integer threadFlag, RLock lock) {
            this.threadFlag = threadFlag;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("第"+threadFlag+"线程获取到了锁");
                //等到1秒后释放锁
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    lock.unlock();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
