package aaron.ren.collection.streamandcollectors;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsDemo {

    public static void main(String[] args) {
        // toCollection
        List<String> list = Arrays.asList("123","123","456","789","1101","212121121","asdaa","3e3e3e","2321eew");
      /*  LinkedList<String> linkedList = list.stream().collect(Collectors.toCollection(LinkedList::new));
        linkedList.forEach((str)-> System.out.println(str));

        //toList
        List<String> arraylist = list.stream().collect(Collectors.toList());
        arraylist.forEach((str)-> System.out.println(str));

        //toSet
        Set<String> hashSet = list.stream().collect(Collectors.toSet());
        hashSet.forEach((str)-> System.out.println(str));

        //joining
        String collect1 = list.stream().collect(Collectors.joining());
        System.out.println(collect1);

        String collect2 = list.stream().collect(Collectors.joining("-"));
        System.out.println(collect2);

        String collect3 = list.stream().collect(Collectors.joining("-", "START", "END"));
        System.out.println(collect3);


        //mapping
        List<Integer> integerList = list.stream().limit(5).collect(Collectors.mapping(Integer::valueOf, Collectors.toList()));
        integerList.forEach((integer)-> System.out.println(integer));*/


        //实现分组功能     收集的方式 收集的构造方法  收集最终呈现的东西
        Map<Integer,List<String>> s = list.stream().collect(Collectors.groupingBy(String::length));
        Map<Integer,List<String>> ss = list.stream().collect(Collectors.groupingBy(String::length, Collectors.toList()));
        Map<Integer,Set<String>> sss = list.stream().collect(Collectors.groupingBy(String::length,HashMap::new,Collectors.toSet()));
        Map<Integer,Set<String>> ssss = list.stream().collect(Collectors.groupingBy(String::length,Collectors.toSet()));


        System.out.println(s);
        System.out.println(ss);
        System.out.println(sss);
        System.out.println(ssss);

    }
}
