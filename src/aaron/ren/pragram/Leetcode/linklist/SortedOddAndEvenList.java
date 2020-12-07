package aaron.ren.pragram.Leetcode.linklist;

/**
 * 单链表奇数递增偶数递减，使之升序
 * 分三步：
 * 1.拆分成2个链表
 * 2.对逆序的链表反转
 * 3.合并2个链表
 */
public class SortedOddAndEvenList {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 这一步注意细节
    public static ListNode[] getTwoList(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode l1 = head;
        ListNode l2 = head.next;
        ListNode next = null;
        ListNode copyNode = null;
        while (l1 != null) {
            next = l1.next.next;
            copyNode = l1.next;
            l1.next = next;
            copyNode.next = next == null ? null : next.next;
            l1 = next;
        }
        return new ListNode[]{head, l2};
    }

    public static ListNode reverse(ListNode head) {
        ListNode pre = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static ListNode mergeListNode(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(9);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(8);
        ListNode[] twoList = getTwoList(head);
        ListNode l1 = twoList[0];
        ListNode l2 = reverse(twoList[1]);
        ListNode res = mergeListNode(l1, l2);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}