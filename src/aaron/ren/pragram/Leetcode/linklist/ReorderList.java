package aaron.ren.pragram.Leetcode.linklist;

/**
 * ¡¥±Ì∂‘’€
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null && p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        if (p1 != null) {
            p2 = p1.next;
            p1.next = null;
        }
        ListNode pre = reverseList(p2);
        mergeLists(head, pre);
    }

    public ListNode reverseList(ListNode p) {
        if (p == null)
            return p;
        ListNode pre = p;
        p = p.next;
        pre.next = null;
        while (p != null) {
            ListNode tmp = p;
            p = p.next;
            tmp.next = pre;
            pre = tmp;
        }
        return pre;
    }

    public void mergeLists(ListNode head, ListNode pre) {
        ListNode p1 = head;
        ListNode p2 = pre;
        ListNode tmp = null;
        while (p1 != null && p2 != null) {
            tmp = p1.next;
            p1.next = p2;
            p1 = tmp;
            tmp = p2.next;
            if (p1 == null) {
                break;
            }
            p2.next = p1;
            p2 = tmp;
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
