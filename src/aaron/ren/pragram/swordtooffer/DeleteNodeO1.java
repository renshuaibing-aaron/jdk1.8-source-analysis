package aaron.ren.pragram.swordtooffer;

/**
 * 1-2-3-4-5
 * ��O(1) ʱ�临�Ӷ�ɾ������Ľڵ�
 *
 *ע����� ���������˼·ת�� ���԰� ���ö�����ָ��
 *
 */
public class DeleteNodeO1 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode listNode = removeNode(node1, node5);
        System.out.println(listNode.val);
        System.out.println(node1.val);

        ListNode  head= node1;
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }



    }

    /**
     * o(1) ɾ��ָ��Ԫ��
     */
    public static ListNode removeNode(ListNode head, ListNode toRemove) {
        if (head == null || toRemove == null) {
            return head;
        }
        //�㷨˼· ������ڵ���¸��ڵ㸴������ڵ� Ȼ���ýڵ��ָ��ָ�����¸��ڵ�
        //����ط��� ��ɾ���Ľڵ㲻��β�ڵ�
        if (toRemove.next != null) {
            toRemove.val = toRemove.next.val;
            toRemove.next = toRemove.next.next;
            return head;
        } else {
            //ɾ������ͷ���
            if (head == toRemove) {
                return null;
            }
            //ɾ������β�ڵ� ֻ�ܱ���
            ListNode lpr=  head;
            while (lpr != null) {
                if (lpr.next == toRemove) {
                    lpr.next = null;
                    break;
                }
                lpr = lpr.next;

            }
            head=head.next;
            head.val=44444;
            System.out.println(head.val);
            return head;
        }

    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int element) {
            this.val = element;
        }
    }
}
