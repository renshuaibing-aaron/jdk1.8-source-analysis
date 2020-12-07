package aaron.ren.pragram.Leetcode.code;

/*
1-2-3\
      4-5-6
  7-8/
* 输入两个链表，找出它们的第一个公共结点
* 1.第一种思路是遍历一个链表然后 在这个基础上遍历另外一个 时间复杂度O(mn)
  2.由图可知 这是个Y的图形 然后 可以利用栈的只是 从后往前进行
  3.找出两个链表的长度差 让长的先走n步 找出第一个相同的节点就是答案
*
* */
public class Solution36 {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        return null;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int element) {
            this.val = element;
        }
    }
}