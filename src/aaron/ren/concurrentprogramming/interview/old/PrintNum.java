package aaron.ren.concurrentprogramming.interview.old;

class PrintNum implements Runnable{
    int num=1;
    @Override
    public void run() {
        synchronized (this) {
            while(true){
                notify();//唤醒wait()的一个或者所有线程
                if (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":"  + num);
                    num++;
                } else {
                    break;
                }
                try {
                    wait();//释放当前的锁，另一个线程就会进来
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
