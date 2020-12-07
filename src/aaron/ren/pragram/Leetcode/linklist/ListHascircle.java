package aaron.ren.pragram.Leetcode.linklist;

/**
 * https://blog.csdn.net/qq_38765867/article/details/84108762
 * 如何判断一个链表是不是有环
 */
public class ListHascircle {
    public boolean hasCycle(ListNode head) {

        ListNode fast = head;

        ListNode slow = head;

        while (slow != null && fast != null && fast.next != null) {//fast.next的判断：判断接下来是否还有数据

            slow = slow.next;

            fast = fast.next.next;

            if (slow == fast)

                return true;

        }

        return false;

    }
    private static class ListNode {
        int val;
        ListNode next;
        ListNode( int element) {
            this.val = element;
        }
    }
}
