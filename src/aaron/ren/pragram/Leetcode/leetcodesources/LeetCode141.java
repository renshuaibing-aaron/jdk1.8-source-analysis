package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.HashSet;
import java.util.Set;

/**
 * ����һ�������ж��������Ƿ��л���
 * Ϊ�˱�ʾ���������еĻ�������ʹ������ pos ����ʾ����β���ӵ������е�λ�ã������� 0 ��ʼ����
 * ��� pos �� -1�����ڸ�������û�л���
 */
public class LeetCode141 {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }
    private static class ListNode {
        int val;
       ListNode next;

        ListNode(int element) {
            this.val = element;
        }
    }
}
