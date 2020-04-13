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

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer next = iterator.next();
            if (next == 1) {
                iterator.remove();
            }
        }


        System.out.println(list.size());
        System.out.println((list));
    }
    }
