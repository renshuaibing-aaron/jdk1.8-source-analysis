package aaron.ren.collection.streamandcollectors;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {

        List<Pair<String,Double>> pairList=new ArrayList<>();

        pairList.add(new Pair<>("version",6.19));
        pairList.add(new Pair<>("version",10.24));
        pairList.add(new Pair<>("version",13.14));

        Map<String, Double> collect = pairList.stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2));

        Set<String> strings = collect.keySet();
        Iterator<String> iterator = strings.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println("key is"+next+"value is"+collect.get(next));
        }
    }
}
