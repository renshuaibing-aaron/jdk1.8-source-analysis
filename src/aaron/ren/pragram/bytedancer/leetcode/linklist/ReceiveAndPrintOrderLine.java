package aaron.ren.pragram.bytedancer.leetcode.linklist;

import java.util.HashMap;

public class ReceiveAndPrintOrderLine {

    public static class Node{
        public int num;        // 节点标号
        public String content; // 节点内容
        public Node next;

        public Node(int num, String content){
            this.num = num;
            this.content = content;
        }
    }

    private HashMap<Integer, Node> headMap = new HashMap<>();
    private HashMap<Integer, Node> tailMap = new HashMap<>();
    private int lastPrint = 0;

    public void receive(int num, String content){
        if(num < 1){
            return;
        }
        // 将消息标号和内容构建成链表节点的形式
        Node current = new Node(num, content);
        headMap.put(num, current);
        tailMap.put(num, current);
        // 这个数前面有一个链表，它要成为新的尾部，它也不再是头了
        if(tailMap.containsKey(num - 1)){
            // 插入到原来链表的尾部
            tailMap.get(num - 1).next = current;
            tailMap.remove(num - 1);  // num-1不再是尾部了
            headMap.remove(num); // num不再是头部了
        }
        // 这个数后面有一个链表，它将成为新的头，它也不再是尾了
        if(headMap.containsKey(num + 1)){
            current.next = headMap.get(num + 1);
            tailMap.remove(num);    // num不再是尾结点了
            headMap.remove(num + 1);  // num+1不再是头结点了
        }
        // 判断打印时机：现在的头结点是否是lastPrint+1
        if(headMap.containsKey(lastPrint + 1)){
            print();
        }
    }

    public void print(){
        Node node = headMap.get(++lastPrint);
        headMap.remove(node);
        while(node != null){
            System.out.println(node.num + " : " + node.content);
            node = node.next;
            lastPrint++;
        }
        tailMap.remove(--lastPrint);
        System.out.println();
    }
}