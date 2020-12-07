package aaron.ren.pragram.Leetcode.linklist;

/**
 * 删除链表中重复节点
 * 1-2-3-4-4-5-6-7-7
 */
public class DeleteMuliNode {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(6);
        ListNode node8 = new ListNode(7);
        ListNode node9 = new ListNode(8);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;

        deleteDuplication(node1);

        while(node1!=null){
            System.out.println(node1.val);
            node1=node1.next;
        }

    }

    public static ListNode deleteDuplication2(ListNode pHead) {
        if (pHead.next == null) {
            return pHead;
        }
        ListNode q = pHead.next;
        while (q != null) {
            if (pHead.val != q.val) {
                pHead=pHead.next;
                q = q.next;
            }else{
                q = q.next;
            }

        }
return  pHead;
    }

    public static ListNode deleteDuplication(ListNode pHead) {
        ListNode first = new ListNode(-1);
        first.next = pHead;
        ListNode last = first;
        ListNode p = pHead;
        //两层循环  两个指针
        while (p != null && p.next != null) {
            if (p.val == p.next.val) {
                int val = p.val;
                while (p != null && p.val == val) {
                    p = p.next;
                    last.next = p;
                }
            } else {
                last = p;
                p = p.next;
            }
        }
        return first.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int element) {
            this.val = element;
        }
    }
}
