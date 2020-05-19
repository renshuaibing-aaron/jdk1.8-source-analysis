package aaron.ren.collection.hashmap;

import java.util.HashMap;

/**
 *�������Ҫ��Ϊ����ʾ HashMap���̲߳���ȫ��
 * ��Ҫ������Hash�ڲ���������� ��������ʱ ����������ѭ��(�γɻ�״)
 *
 * Ȼ����ִ�в���get���� ������ѭ�� ����cpu100%����
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
