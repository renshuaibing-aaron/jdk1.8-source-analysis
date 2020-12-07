package aaron.ren.collection.streamandcollectors;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class StreamDemo {

    public static void main(String[] args) {
        //����������
        //����ͨ�����ַ�ʽ������ ����Ļ��Ǽ��ϵ���ʽ

        //�����м����

        //���˲���
        List<String> stringsList = Arrays.asList("123", "456", "789", "abc3d", "efgh", "hijk");

        List<String> collect = stringsList.stream().filter(e -> e.length() > 4 && e.length() < 7)
                .peek(System.out::println)
                .collect(Collectors.toList());



    }
}
