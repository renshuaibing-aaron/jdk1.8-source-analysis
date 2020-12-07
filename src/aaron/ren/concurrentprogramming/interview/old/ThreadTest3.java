package aaron.ren.concurrentprogramming.interview.old;


public class ThreadTest3 {
    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(new PrintOdd(counter)).start();
        new Thread(new PrintEven(counter)).start();
    }
}
