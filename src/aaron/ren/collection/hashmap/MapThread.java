package aaron.ren.collection.hashmap;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapThread extends Thread{
    /**
     * 类的静态变量是各个实例共享的，因此并发的执行此线程一直在操作这两个变量
     * 选择AtomicInteger避免可能的int++并发问题
     */
    private static AtomicInteger ai = new AtomicInteger(0);
    //初始化一个table长度为1的哈希表
    private static HashMap map = new HashMap(1);
    //如果使用ConcurrentHashMap，不会出现类似的问题
    //       private static ConcurrentHashMap map = new ConcurrentHashMap(1);

    @Override
    public void run()
    {
        while (ai.get() < 100000)
        {  //不断自增
            map.put(ai.get(), ai.get());
            ai.incrementAndGet();
        }

        System.out.println(Thread.currentThread().getName()+"线程即将结束");
    }


    public static void main(String[] args){
        MapThread t0 = new MapThread();
        MapThread t1 = new MapThread();
        MapThread t2 = new MapThread();
        MapThread t3 = new MapThread();
        MapThread t4 = new MapThread();
        MapThread t5 = new MapThread();
        MapThread t6 = new MapThread();
        MapThread t7 = new MapThread();
        MapThread t8 = new MapThread();
        MapThread t9 = new MapThread();

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();

    }
}