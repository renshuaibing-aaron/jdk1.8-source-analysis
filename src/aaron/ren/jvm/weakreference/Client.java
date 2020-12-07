package aaron.ren.jvm.weakreference;

/**
 *https://www.jianshu.com/p/964fbc30151a
 */

public class Client {
    public static void main(String[] args) {

        //todo 特别注意这个 Apple对象只有一个弱引用 就是salad 没有强引用 并且这个 弱引用放在堆里面
        //注意和Threadlocal的结合理解
        // static class Entry extends WeakReference<ThreadLocal<?>>
        Salad salad = new Salad(new Apple("红富士"));
        //通过WeakReference的get()方法获取Apple
        System.out.println("Apple:" + salad.get());
        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //如果为空，代表被回收了
        if (salad.get() == null) {
            System.out.println("clear Apple。");
        }
    }
}
