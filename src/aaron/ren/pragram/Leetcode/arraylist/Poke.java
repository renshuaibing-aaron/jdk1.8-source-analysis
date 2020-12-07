package aaron.ren.pragram.Leetcode.arraylist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 思路是一直还原扑克牌的前一次状态，从最后两张牌开始，将牌桌上最后两张牌拿在手里（顺序就是牌桌上的顺序）。
 * 第一步还原前一次操作：把底下那张扑克牌置顶；第二步还原，将桌面上牌组的最后一张牌插入到手中牌堆顶部。对
 * 应的放牌顺序中（1.从牌堆顶取一张放桌子上；2.再取一张放牌堆底）重复这两步操作直到牌桌上没有牌。
 * 要频繁地插入和将底部元素置顶，所以用到了队列Queue和链表LinkedList。
 */
public class Poke {
    public static void main(String[] args) {
        int[] a = {1,3,5,4,2};
        System.out.println(sort(a));
    }

    public static LinkedList<Integer> sort(int[] a) {
        int n = a.length;
        LinkedList<Integer> list = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        list.add(a[n-2]);
        list.add(a[n-1]);
        int i = n-3;
        while(i>=0) {
            int len = list.size();
            for(int j=0;j<len-1;j++) {
                queue.add(list.remove(0));
            }
            while(!queue.isEmpty()) {
                list.add(queue.poll());
            }
            len = list.size();
            for(int j=0;j<len;j++) {
                queue.add(list.remove(0));
            }
            list.add(a[i]);
            while(!queue.isEmpty()) {
                list.add(queue.poll());
            }
            i--;
        }
        return list;
    }

}


