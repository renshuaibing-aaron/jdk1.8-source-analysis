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


        //Ĭ��������
        list.forEach((str)-> System.out.println(str));


        //�Զ���ʵ���ý���
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

        //Ĭ��������
        strlist.forEach((str)-> System.out.println(str));


        //�Զ���ʵ���ý���
        Collections.sort(strlist, new Comparator<String>() {
            @Override    //��Ҫ�������ģ���ʹ��
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        strlist.forEach((str)-> System.out.println(str));

    }
}
