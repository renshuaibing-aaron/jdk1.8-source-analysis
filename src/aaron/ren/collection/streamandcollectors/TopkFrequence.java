package aaron.ren.collection.streamandcollectors;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopkFrequence {

    public static void main(String[] args) {
        // ��Streamת����������Map
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));

        System.out.println(map);
    }

    //���ַ��������� ���ճ��ֵĸ����������� ���ҳ���topk����
    public static List<String> topKFrequent(String[] words, int k) {

        Map<String, Long> collect = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        //������� ��Ҫ̫���㰡
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
