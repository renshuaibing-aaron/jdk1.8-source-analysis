package aaron.ren.pragram.swordtooffer;

/*
1-2-3\
      4-5-6
  7-8/
* �������������ҳ����ǵĵ�һ���������
* 1.��һ��˼·�Ǳ���һ������Ȼ�� ����������ϱ�������һ�� ʱ�临�Ӷ�O(mn)
  2.��ͼ��֪ ���Ǹ�Y��ͼ�� Ȼ�� ��������ջ��ֻ�� �Ӻ���ǰ����
  3.�ҳ���������ĳ��Ȳ� �ó�������n�� �ҳ���һ����ͬ�Ľڵ���Ǵ�
*
* */
public class FindFirstCommonNode {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node7.next = node8;
        node8.next = node4;

        ListNode listNode = FindFirstCommonNode(node7, node1);

        System.out.println(listNode.val);

    }

    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

        if(pHead1==null||pHead2==null){
            return null;
        }
        int l1 = listLength(pHead1);
        int l2 = listLength(pHead2);
        int i = Math.abs(l1 - l2);

        System.out.println(i);
        ListNode lp1 = pHead1;
        ListNode lp2 = pHead2;
        if (l1 >= l2) {
            while (i > 0) {
                lp1 = lp1.next;
                i--;
            }
            while (lp1!=lp2) {
                lp1 = lp1.next;
                lp2 = lp2.next;
            }
            return lp1;
        } else {
            while (i > 0) {
                lp2 = lp2.next;
                i--;
            }
            while (lp1!=lp2) {
                lp1 = lp1.next;
                lp2 = lp2.next;
            }
            return lp1;
        }

    }

    public static int listLength(ListNode head) {

        ListNode first = head;
        int length = 0;
        while (first != null) {
            length++;
            first = first.next;

        }
        return length;

    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int element) {
            this.val = element;
        }
    }
}
