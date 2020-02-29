package aaron.ren.collection.hashmap;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MapThread extends Thread{
    /**
     * ��ľ�̬�����Ǹ���ʵ������ģ���˲�����ִ�д��߳�һֱ�ڲ�������������
     * ѡ��AtomicInteger������ܵ�int++��������
     */
    private static AtomicInteger ai = new AtomicInteger(0);
    //��ʼ��һ��table����Ϊ1�Ĺ�ϣ��
    private static HashMap map = new HashMap(1);
    //���ʹ��ConcurrentHashMap������������Ƶ�����
    //       private static ConcurrentHashMap map = new ConcurrentHashMap(1);

    @Override
    public void run()
    {
        while (ai.get() < 100000)
        {  //��������
            map.put(ai.get(), ai.get());
            ai.incrementAndGet();
        }

        System.out.println(Thread.currentThread().getName()+"�̼߳�������");
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