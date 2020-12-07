package aaron.ren.pragram.swordtooffer;

/**
 * 1-2-3-4-5
 * 以O(1) 时间复杂度删除链表的节点
 *
 *注意理解 链表操作的思路转换 可以把 引用对象当做指针
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
     * o(1) 删除指定元素
     */
    public static ListNode removeNode(ListNode head, ListNode toRemove) {
        if (head == null || toRemove == null) {
            return head;
        }
        //算法思路 把这个节点的下个节点复制这个节点 然后让节点的指针指向下下个节点
        //这个地方是 待删除的节点不是尾节点
        if (toRemove.next != null) {
            toRemove.val = toRemove.next.val;
            toRemove.next = toRemove.next.next;
            return head;
        } else {
            //删除的是头结点
            if (head == toRemove) {
                return null;
            }
            //删除的是尾节点 只能遍历
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
