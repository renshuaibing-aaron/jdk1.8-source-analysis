package aaron.ren.collection.streamandcollectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopkFrequence {

    public static void main(String[] args) {
        // 将Stream转换成容器或Map
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));

        System.out.println(map);
    }

    //在字符串数组中 按照出现的个数进行排序 查找出现topk问题
    public static List<String> topKFrequent(String[] words, int k) {

        Map<String, Long> collect = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        //这个特码 不要太优秀啊
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((o1, o2) -> {
                    if (o1.getValue().equals(o2.getValue())) {
                        return o1.getKey().compareTo(o2.getKey());
                    } else {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                })
                .map(Map.Entry::getKey)
                .limit(k)
                .collect(Collectors.toList());

    }
}
