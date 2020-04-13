package aaron.ren.concurrentprogramming.whilenotify;


/*
* https://www.cnblogs.com/lyuweigh/p/9568697.html
* https://www.iteye.com/blog/uule-1101994
*
*
* ע�⣺�������߳�B�е������߳�A��Join()������ֱ���߳�Aִ����Ϻ󣬲Ż����ִ���߳�B�� ��������ô���
* */
public class ThreadJoinTest {
    public static void main(String[] args) throws InterruptedException {

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<10) {
                    System.out.println(" i am A");
                    i++;
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<10) {
                    System.out.println(" i am B");
                    i++;
                }
            }
        });




        b.start();
        a.start();
        a.join();


        System.out.println(" i am main");
    }


}
