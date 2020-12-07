package aaron.ren;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(i);
            set.remove(i-1);
        }

        System.out.println(set.size());



        Set<Short> set2 = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            set2.add(i);
            set2.remove(i-1);
        }
        System.out.println(set.size());

        Object i = 1 == 1 ? new Integer(3) : new Float(1);
        System.out.println(i);



        // id, name
        Map<Long, String> map = new HashMap<>();
        map.put(1L, "1111");
        map.put(2L, "2222");
        map.put(3L, "3333");

        // ������Ҫ���õ�һ�����������long[] ���ͣ�
        // map.keySet()�����õ�id���б�toArray��������ת������
        // ArrayUtils.toPrimitive�������԰Ѷ�������ת��Ϊ������������
        // Ȼ���Ҿ�����д��
        //long[] ids = ArrayUtils.toPrimitive(map.keySet().toArray(new Long[0]));
    }
}
