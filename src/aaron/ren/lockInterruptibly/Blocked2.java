package aaron.ren.lockInterruptibly;

class Blocked2 implements Runnable {
    BlockedMutex blocked = new BlockedMutex();


    @Override
    public void run() {
        System.out.println("Waiting for f() in BlockedMutex");
        this.blocked.f();
        System.out.println("Broken out of blocked call");
        System.out.println(Thread.currentThread().getName());
    }


}
