package aaron.ren.jvm.weakreference;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new WeakHashMap<>(3);

        // ����3��new String()�������ַ���
        map.put(new String("1"), 1);
        map.put(new String("2"), 2);
        map.put(new String("3"), 3);

        // ���벻��new String()�������ַ���
        map.put("6", 6);

        // ʹ��keyǿ����"3"����ַ���
        String key = null;
        for (String s : map.keySet()) {
            // ���"3"��new String("3")����һ������
            if (s.equals("3")) {
                key = s;
            }
        }

        // ���{6=6, 1=1, 2=2, 3=3}��δgc����key�����Դ�ӡ����
        System.out.println(map);

        // gcһ��
        System.gc();

        // ��һ��new String()�������ַ���
        map.put(new String("4"), 4);

        // ���{4=4, 6=6, 3=3}��gc������ֵ��ǿ���õ�key���Դ�ӡ����
        System.out.println(map);

        // key��"3"�����ö���
        key = null;

        // gcһ��
        System.gc();

        // ���{6=6}��gc��ǿ���õ�key���Դ�ӡ����
        System.out.println(map);
    }
}
