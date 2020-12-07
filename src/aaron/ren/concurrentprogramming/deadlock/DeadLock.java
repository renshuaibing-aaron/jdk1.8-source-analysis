package aaron.ren.concurrentprogramming.deadlock;

/**
 * The type Dead lock.
 *
 * @author yujuan
 * @time 2019 /08/22 16:16:42
 */
public class DeadLock {
    /**
     * The constant obj1.
     *
     */
    public static String obj1 = "obj1";
    /**
     * The constant obj2.
     */
    public static String obj2 = "obj2";

    /**
     * Main.
     *
     * @param args the args
     * @author yujuan
     * @time 2019 /08/22 16:16:42
     */
    public static void main(String[] args) {
        Thread a = new Thread(new Lock1());
        Thread b = new Thread(new Lock2());
        a.start();
        b.start();
    }
}