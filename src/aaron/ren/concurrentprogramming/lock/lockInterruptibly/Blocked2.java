package aaron.ren.concurrentprogramming.lock.lockInterruptibly;

class Blocked2 implements Runnable {
    BlockedMutex blocked = new BlockedMutex();


    @Override
    public void run() {
        System.out.println("Waiting for f() in BlockedMutex");
        try {
            blocked.f();
        } catch (Exception e) {
            System.out.println("��ִ�з�����ʱ���������쳣");
        }
        System.out.println("Broken out of blocked call");
        System.out.println(Thread.currentThread().getName());
    }


}
