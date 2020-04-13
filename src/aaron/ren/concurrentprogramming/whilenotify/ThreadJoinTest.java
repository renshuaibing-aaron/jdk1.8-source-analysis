package aaron.ren.concurrentprogramming.whilenotify;


/*
* https://www.cnblogs.com/lyuweigh/p/9568697.html
* https://www.iteye.com/blog/uule-1101994
*
*
* 注意：比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。 这个很有用处的
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
