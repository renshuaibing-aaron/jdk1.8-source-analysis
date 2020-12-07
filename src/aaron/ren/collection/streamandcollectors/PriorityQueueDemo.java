package aaron.ren.collection.streamandcollectors;

import java.util.*;

public class PriorityQueueDemo {

    public static void main(String[] args) {


        // todo  ע�����ѻ���С����(���ȼ�����)ֻ�ܱ�֤��ͷ��Ԫ���ǵ�ǰ���е����(������С)  ������Ԫ�ز�û������
        PriorityQueue<Integer> smallroot = new PriorityQueue<>();

        smallroot.add(10);
        smallroot.add(2);
        smallroot.add(3);
        smallroot.add(4);
        smallroot.add(6);
        smallroot.add(1);

        System.out.println(smallroot);



   //����Ϊʲôû����Ч��
    Collections.sort(new ArrayList<Integer>(smallroot));
        System.out.println(smallroot);



        //�����
        PriorityQueue<Integer> bigroot = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        //PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> (o2 - o1));  �����ģ��

        bigroot.add(10);
        bigroot.add(2);
        bigroot.add(3);
        bigroot.add(4);
        bigroot.add(6);
        bigroot.add(1);
        System.out.println(bigroot);



        PriorityQueue<String> bigString = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return  o2.compareTo(o1) ;
            }
        });

        bigString.add("a");
        bigString.add("b");
        bigString.add("c");
        bigString.add("d");
        bigString.add("e");

        System.out.println(bigString);


        Collections.sort(new ArrayList<String>(bigString));
        System.out.println(bigString);



        PriorityQueue<String> samllString = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return  o1.compareTo(o2) ;
            }
        });

        samllString.add("a");
        samllString.add("b");
        samllString.add("c");
        samllString.add("d");
        samllString.add("e");

        System.out.println(samllString);




    }
}
