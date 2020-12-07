package aaron.ren.collection.streamandcollectors;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args) {
        //创建流操作
        //可以通过多种方式创建流 最常见的还是集合的形式

        //流的中间操作

        //过滤操作
        List<String> stringsList = Arrays.asList("123", "456", "789", "abc3d", "efgh", "hijk");

        List<String> collect = stringsList.stream().filter(e -> e.length() > 4 && e.length() < 7)
                .peek(System.out::println)
                .collect(Collectors.toList());



    }
}
