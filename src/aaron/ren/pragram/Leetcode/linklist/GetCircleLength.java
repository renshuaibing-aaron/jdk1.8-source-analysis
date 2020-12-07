package aaron.ren.pragram.Leetcode.linklist;

/**
 * https://blog.csdn.net/plokmju88/article/details/103675872
 * 单向链表找环，求环的长度
 * https://blog.csdn.net/sinat_35261315/article/details/79205157
 */
public class GetCircleLength {

    int cycleLen(ListNode head) {
        ListNode walker = head;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next;
            if (walker == runner) {
                break;
            }
        }
        int len = 0;
        while (runner != null && runner.next != null) {
            ++len;
            walker = walker.next;
            runner = runner.next;
            if (walker == runner) {
                break;
            }
        }
        return len;
    }

    private static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
