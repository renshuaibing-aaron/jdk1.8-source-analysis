package aaron.ren.pragram.bytedancer.leetcode.linklist;

import java.util.Stack;

public class IsPalindrome {

    //1 2 3 3 2 1

    public boolean isPalindrome(ListNode head) {

        if (head == null && head.next == null) {
            return true;
        }
        Stack<ListNode> stack = new Stack();

        ListNode lpr = head;

        while (lpr != null) {
            stack.push(lpr);
            lpr = lpr.next;
        }

        while (head != null) {
            ListNode peek = stack.peek();
            if (peek.val != head.val) {
                return false;
            } else {
                stack.pop();
                head = head.next;
            }
        }
        return true;

    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int element) {
            this.val = element;
        }
    }

}
