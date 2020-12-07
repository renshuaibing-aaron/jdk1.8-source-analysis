package aaron.ren.concurrentprogramming.thread;

public class Run {
    public static void main(String args[]){
        Thread thread = new MyThread();
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("====main thread exit========");

    }

    private static class MyThread extends Thread {
        @Override
        public void run(){
            super.run();
            for(int i=0; i<500000; i++){
                if(interrupted()) {
                    System.out.println("线程已经终止， for循环不再执行");
                    break;
                }
                System.out.println("i="+(i+1));
            }
        }
    }
}