package aaron.ren.pragram.Leetcode.linklist;

/**
 * 高级链表反转 部分反转
 *  1->2->3->4->5
 */
public class ReverseListAd {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        reverseKGroupNew(node1,2);

        while(node1!=null){
            System.out.println(node1.val);
            node1=node1.next;
        }


    }
    /**
     * 使用递归进行反转
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroupNew(ListNode head, int k) {
        // 判断链表中元素的个数
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
            // k - 1是因为next到了k个
            if (count == k - 1) {
                break;
            }
        }
        // 如果当前temp的下一个节点为空，说明个数不够k，则直接返回
        if (current == null) {
            return head;
        }

        int num = k;       // 保存翻转的次数
        ListNode tempHead = head;  // 保存递归后连接的头结点
        // 翻转链表
        ListNode pre = null;
        while (num > 0) {
            // 在head的next指针断开前，先保存它的next指针指向的对象
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            num--;
        }
        // current.next为下一次翻转的头结点
        current = current.next;
        tempHead.next = reverseKGroupNew(current, k);
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int len = 0;
        ListNode tmp = head;
        //计算链表长度
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }
        len /= k;
        //当没有要反转链表的时候直接返回
        if (len == 0) {
            return head;
        }
        ListNode cur = head;
        ListNode tail = cur;
        for (int i = 0; i < len; i++) {
            //newlist表示分离出来的链表
            ListNode newlist = null;
            //newhead表示分离出来链表的头
            ListNode newhead = cur;
            int count = k;
            //反转分离出来的链表
            while (count > 0) {
                tmp = cur;
                cur = cur.next;
                tmp.next = newlist;
                newlist = tmp;
                count--;
            }
            //只有当第一次反转链表时不用将首尾相连，因为只有头，所以只需要将链表原本的头
            //更新即可，在后面的链表反转中，则需要将分离出来的链表与之前的链表首尾相连
            if (i == 0) {
                head = newlist;
            } else {
                tail.next = newlist;
                tail = newhead;
            }
        }
        //链接链表剩余的部分
        while (cur != null) {
            tail.next = cur;
            tail = tail.next;
            cur = cur.next;
        }
        //返回头节点
        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int element) {
            this.val = element;
        }
    }
}
