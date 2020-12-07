package aaron.ren.concurrentprogramming.deadlock;

/**
 * The type Lock 1.
 *
 * @author yujuan
 * @time 2019 /08/22 16:16:42
 */
class Lock1 implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Lock1 running");
            while (true) {
                synchronized (DeadLock.obj1) {
                    System.out.println("Lock1 lock obj1");
                    Thread.sleep(3000);//��ȡobj1���ȵ�һ�������Lock2���㹻��ʱ����סobj2
                    synchronized (DeadLock.obj2) {
                        System.out.println("Lock2 lock obj2");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}