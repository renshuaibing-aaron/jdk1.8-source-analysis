package aaron.ren.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(1);
        System.out.println(list.size());
        System.out.println((list));

        for(int i=0;i<list.size();i++){
            if(list.get(i)==4){
                list.remove(i);
            }

        }

        System.out.println("**************1**************");
        System.out.println(list.size());
        System.out.println((list));


        for(Integer item :list){
            if(item==2){
                list.remove(item);
            }

        }
        System.out.println(list.size());
        System.out.println((list));

        System.out.println("***********2*****************");


        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(1);


        Iterator<Integer> iterator = list2.iterator();
        while(iterator.hasNext()){
            Integer next = iterator.next();
            if (next == 1) {
                iterator.remove();
            }
        }


        System.out.println(list2.size());
        System.out.println((list2));
    }
    }
