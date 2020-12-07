package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 *���������������ϲ�Ϊһ���µ� ���� ���������ء���������ͨ��ƴ�Ӹ������������������нڵ���ɵġ�?
 * ʾ����
 * ���룺1->2->4, 1->3->4
 * �����1->1->2->3->4->4
 */
public class LeetCode21 {
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            else if (l2 == null) {
                return l1;
            }
            else if (l1.val < l2.val) {
                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            }
            else {
                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }

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