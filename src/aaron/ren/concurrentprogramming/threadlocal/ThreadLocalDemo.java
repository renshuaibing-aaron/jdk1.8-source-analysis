package aaron.ren.concurrentprogramming.threadlocal;

public class ThreadLocalDemo {

    // ����ThreadLocal & ��ʼ��
    private  static  ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "��ʼ��ֵ";
        }
    };
    // ���Դ���
    public static void main(String[] args){
        // �¿�2���߳��������� & ��ȡ ThreadLoacl��ֵ
        MyRunnable runnable = new MyRunnable();
        new Thread(runnable, "�߳�1").start();
        new Thread(runnable, "�߳�2").start();
    }

    // �߳���
    public static class MyRunnable implements Runnable {



        @Override
        public void run() {

            // �����߳�ʱ���ֱ����� & ��ȡ ThreadLoacl��ֵ
            String name = Thread.currentThread().getName();
            threadLocal.set(name + "��threadLocal"); // ����ֵ = �߳���
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "��" + threadLocal.get());
        }
    }


    //
}
