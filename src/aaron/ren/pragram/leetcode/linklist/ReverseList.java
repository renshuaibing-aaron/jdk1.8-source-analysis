package aaron.ren.pragram.Leetcode.linklist;

/**
 * 1->2->3->4->5
 *
 * 5->4->3->2->1
 *
 * 链表反转的
 * 1.就地反转
 * 2.头插法反转
 * 3.递归反转
 * https://blog.csdn.net/superxiaolong123/article/details/86687733
 * https://blog.csdn.net/w605283073/article/details/86653745
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;

       // ListNode listNode = reverseListByLocal(node1);
        ListNode listNode2 = reverseListByInsert(node1);


        ListNode head2=new ListNode(-1);
        head2.next=listNode2;
        while(head2.next!=null){
            System.out.println(head2.next.val);
            head2.next=head2.next.next;
        }

    }

    /**
     * 就地反转
     * @param listNode
     * @return
     */
    public static ListNode reverseListByLocal(ListNode listNode) {
        ListNode resultList = new ListNode(-1);
        resultList.next = listNode;
        ListNode p = listNode;
        ListNode pNext = p.next;
        while (pNext != null) {
            p.next = pNext.next;
            pNext.next = resultList.next;
            resultList.next = pNext;
            pNext = p.next;
        }
        return resultList.next;
    }

    /**
     * 头插法插入
     * @param listNode
     * @return
     */
    public static ListNode reverseListByInsert(ListNode listNode) {
        //定义一个带头节点的
        ListNode resultList = new ListNode(-1);
        //循环节点
        ListNode p = listNode;
        while (p != null) {
            //保存插入点之后的数据
            ListNode tempList = p.next;
            p.next=resultList.next ;
            resultList.next = p;
            p = tempList;
        }
        return resultList.next;
    }

    /**
     * 使用递归
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        // 遍历到链表尾部
        ListNode newHead = reverseList(head.next);
        // 反转
        head.next.next = head;
        head.next = null;

        return newHead;
    }



        private static class ListNode {
        int val;        ListNode next;

            ListNode(int element) {
                this.val = element;
            }
        }

}
