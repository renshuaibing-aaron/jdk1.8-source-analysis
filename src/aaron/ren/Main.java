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

        // 后面需要调用的一个方法入参是long[] 类型，
        // map.keySet()可以拿到id的列表，toArray方法可以转化数组
        // ArrayUtils.toPrimitive方法可以把对象数据转化为基本类型数组
        // 然后，我就这样写了
        //long[] ids = ArrayUtils.toPrimitive(map.keySet().toArray(new Long[0]));
    }
}
