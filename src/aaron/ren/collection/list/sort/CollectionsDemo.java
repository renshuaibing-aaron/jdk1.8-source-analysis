package aaron.ren.collection.list.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsDemo {

    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(5);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(2);
        Collections.sort(list);


        //默认是升序
        list.forEach((str)-> System.out.println(str));


        //自定义实现用降序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        list.forEach((str)-> System.out.println(str));



        List<String> strlist=new ArrayList<>();
        strlist.add("a");
        strlist.add("c");
        strlist.add("e");
        strlist.add("b");
        strlist.add("d");
        Collections.sort(strlist);

        //默认是升序
        strlist.forEach((str)-> System.out.println(str));


        //自定义实现用降序
        Collections.sort(strlist, new Comparator<String>() {
            @Override    //主要掌握这个模板的使用
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        strlist.forEach((str)-> System.out.println(str));

    }
}
