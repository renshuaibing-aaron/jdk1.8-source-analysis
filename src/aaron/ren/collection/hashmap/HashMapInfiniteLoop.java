package aaron.ren.collection.hashmap;

import java.util.HashMap;

/**
 *这个类主要是为了演示 HashMap的线程不安全性
 * 主要表现在Hash在并发的情况下 发生扩容时 产生的链表循环(形成环状)
 *
 * 然后在执行查找get操作 触发死循环 导致cpu100%问题
 */
public class HashMapInfiniteLoop {

    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(2,0.75f);
    public static void main(String[] args) {
        map.put(5, 55);

        new Thread("Thread1") {
            @Override
            public void run() {
                map.put(7, 77);
                System.out.println(map);
            };
        }.start();
        new Thread("Thread2") {
            @Override
            public void run() {
                map.put(3, 33);
                System.out.println(map);
            };
        }.start();

    }


}
