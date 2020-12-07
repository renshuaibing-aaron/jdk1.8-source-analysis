package aaron.ren.concurrentprogramming.interview.old;

class PrintNum implements Runnable{
    int num=1;
    @Override
    public void run() {
        synchronized (this) {
            while(true){
                notify();//����wait()��һ�����������߳�
                if (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":"  + num);
                    num++;
                } else {
                    break;
                }
                try {
                    wait();//�ͷŵ�ǰ��������һ���߳̾ͻ����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
