package aaron.ren.concurrentprogramming.interview.old;

public class ThreadTest {
    public static void main(String[] args) {
        PrintNum p=new PrintNum();
        Thread t1=new Thread(p);
        Thread t2=new Thread(p);
        t1.setName("��");
        t2.setName("��");
        t1.start();
        t2.start();
    }
}
