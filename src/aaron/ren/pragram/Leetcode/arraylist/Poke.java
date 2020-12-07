package aaron.ren.pragram.Leetcode.arraylist;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ˼·��һֱ��ԭ�˿��Ƶ�ǰһ��״̬������������ƿ�ʼ��������������������������˳����������ϵ�˳�򣩡�
 * ��һ����ԭǰһ�β������ѵ��������˿����ö����ڶ�����ԭ������������������һ���Ʋ��뵽�����ƶѶ�������
 * Ӧ�ķ���˳���У�1.���ƶѶ�ȡһ�ŷ������ϣ�2.��ȡһ�ŷ��ƶѵף��ظ�����������ֱ��������û���ơ�
 * ҪƵ���ز���ͽ��ײ�Ԫ���ö��������õ��˶���Queue������LinkedList��
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


