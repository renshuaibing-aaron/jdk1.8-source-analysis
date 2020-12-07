package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * 单链表的公共节点
 * 编写一个程序，找到两个单链表相交的起始节点。
 *https://www.cnblogs.com/xiaoxi666/p/7384470.html
 * https://www.jianshu.com/p/52e4b051c82a
 *
 */
public class LC160IntersectNode {
    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);
        ListNode node6=new ListNode(6);
        ListNode node7=new ListNode(7);
        ListNode node8=new ListNode(8);

        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node7;
        node7.next=node8;
        node5.next=node6;
        node6.next=node7;
        test(node1);
        System.out.println(node1.val);
    }


    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode longListNode;
        ListNode shortListNode;
        ListNode resultListNode=null;
        //求出两个链表的长度
        //让长的先走 依次比较是否相等
        int linkLengthA = getLinkLength(headA);
        int linkLengthB = getLinkLength(headB);
        System.out.println(linkLengthA);
        System.out.println(linkLengthB);
        if ((linkLengthA - linkLengthB) > 0) {
            longListNode = headA;
            shortListNode = headB;
        } else {
            longListNode = headB;
            shortListNode = headA;
        }
        System.out.println(longListNode.val);
        System.out.println(shortListNode.val);
        int step = linkLengthA - linkLengthB;
        for (int i = 1; i <= step; i++) {
            longListNode = longListNode.next;
        }
        while (longListNode != null && shortListNode != null) {
            if (longListNode.val == shortListNode.val) {
                resultListNode = longListNode;
                break;
            } else {
                longListNode = longListNode.next;
                shortListNode = shortListNode.next;
            }
        }

        return resultListNode;
    }
    public static int test(ListNode head) {
        int length = 0;

        while (length <4) {
            length++;
            head.val=2;
            head = head.next;
        }
        System.out.println(head.val);
        return length;
    }
    public static int getLinkLength(ListNode head) {
        int length = 0;

        while (head != null) {
            length++;
            head = head.next;
        }
        System.out.println(head.val);
        return length;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
