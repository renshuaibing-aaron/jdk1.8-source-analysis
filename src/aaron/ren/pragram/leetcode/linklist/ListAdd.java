package aaron.ren.pragram.Leetcode.linklist;

/**
 * ��������������������
 * ������ģ���������
 */
public class ListAdd {

    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(2);
        addLists2(l1,l2);
    }
    public static ListNode addLists2(ListNode l1, ListNode l2) {
        // write your code here
        ListNode l11 = reverse(l1);
        ListNode l22 = reverse(l2);
        ListNode p1 = l11;
        ListNode p2 = l22;
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        int flag = 0;
        while (p1 != null || p2 != null) {
            int temp = flag;
            if (p1 != null) {
                temp += p1.val;
                p1 = p1.next;
            }
            if (p2 != null){
                temp += p2.val;
                p2 = p2.next;
            }
            if (temp > 9) {
                temp -= 10;
                flag = 1;
            } else {
                flag = 0;
            }
            ListNode node = new ListNode(temp);
            p.next = node;
            p = p.next;
        }
        if (flag == 1) {
            ListNode node = new ListNode(1);
            p.next = node;
        }
        return reverse(dummy.next);
    }

    public static  ListNode reverse (ListNode head) {
        ListNode newhead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newhead;
            newhead = head;
            head = temp;
        }
        return newhead;
    }

    /**
     * ����������������������������ÿ���ڵ����һ�����֡����ִ洢������ԭ���������෴��˳��ʹ�õ�һ������λ������Ŀ�ͷ��
     * д��һ������������������ӣ���������ʽ���غ͡�
     * ������������ 3->1->5->null �� 5->9->2->null������ 8->0->8->null
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addLists(ListNode l1, ListNode l2) {
        // write your code here
        ListNode dummy = new ListNode(-1);
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode p = dummy;
        int flag = 0;
        while (p1 != null || p2 != null) {
            int temp = flag;
            if (p1 != null) {
                temp += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                temp += p2.val;
                p2 = p2.next;
            }
            if (temp > 9) {
                temp -= 10;
                flag = 1;
            } else {
                flag = 0;
            }
            p.next = new ListNode(temp);
            p = p.next;
        }
        if (flag == 1) {
            p.next = new ListNode(1);
        }
        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode( int element) {
            this.val = element;
        }
    }
}
