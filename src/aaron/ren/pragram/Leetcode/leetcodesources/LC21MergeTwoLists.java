package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * 合并两个有序链表
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class LC21MergeTwoLists {
    public static void main(String[] args) {
        ListNode node1=new ListNode(1) ;
        ListNode node2=new ListNode(2) ;
        ListNode node3=new ListNode(4) ;

        node1.next=node2;
        node2.next=node3;

        ListNode node4=new ListNode(1) ;
        ListNode node5=new ListNode(3) ;
        ListNode node6=new ListNode(4) ;
        node4.next=node5;
        node5.next=node6;

        ListNode listNode = mergeTwoLists2(node1, node4);

        while(listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }
    }


    public static  ListNode mergeTwoLists2(ListNode l1, ListNode l2) {


        ListNode first=new ListNode(0);
        ListNode tail=first;

        while(l1!=null&&l2!=null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
                tail=tail.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
                tail=tail.next;
            }
        }
        while(l1!=null){
            tail.next = l1;
            l1 = l1.next;
            tail=tail.next;
        }
        while(l2!=null){
            tail.next = l2;
            l2 = l2.next;
            tail=tail.next;
        }
        return  first.next;

    }

    /**
     * 用递归实现有序链表的合并
     *   这个太难想到了？？
     * @param l1
     * @param l2
     * @return
     */

    public static  ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
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
