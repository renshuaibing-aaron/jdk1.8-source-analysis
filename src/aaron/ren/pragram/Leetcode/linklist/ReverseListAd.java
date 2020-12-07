package aaron.ren.pragram.Leetcode.linklist;

/**
 * �߼�����ת ���ַ�ת
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
     * ʹ�õݹ���з�ת
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroupNew(ListNode head, int k) {
        // �ж�������Ԫ�صĸ���
        int count = 0;
        ListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
            // k - 1����Ϊnext����k��
            if (count == k - 1) {
                break;
            }
        }
        // �����ǰtemp����һ���ڵ�Ϊ�գ�˵����������k����ֱ�ӷ���
        if (current == null) {
            return head;
        }

        int num = k;       // ���淭ת�Ĵ���
        ListNode tempHead = head;  // ����ݹ�����ӵ�ͷ���
        // ��ת����
        ListNode pre = null;
        while (num > 0) {
            // ��head��nextָ��Ͽ�ǰ���ȱ�������nextָ��ָ��Ķ���
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            num--;
        }
        // current.nextΪ��һ�η�ת��ͷ���
        current = current.next;
        tempHead.next = reverseKGroupNew(current, k);
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        int len = 0;
        ListNode tmp = head;
        //����������
        while (tmp != null) {
            len++;
            tmp = tmp.next;
        }
        len /= k;
        //��û��Ҫ��ת�����ʱ��ֱ�ӷ���
        if (len == 0) {
            return head;
        }
        ListNode cur = head;
        ListNode tail = cur;
        for (int i = 0; i < len; i++) {
            //newlist��ʾ�������������
            ListNode newlist = null;
            //newhead��ʾ������������ͷ
            ListNode newhead = cur;
            int count = k;
            //��ת�������������
            while (count > 0) {
                tmp = cur;
                cur = cur.next;
                tmp.next = newlist;
                newlist = tmp;
                count--;
            }
            //ֻ�е���һ�η�ת����ʱ���ý���β��������Ϊֻ��ͷ������ֻ��Ҫ������ԭ����ͷ
            //���¼��ɣ��ں��������ת�У�����Ҫ�����������������֮ǰ��������β����
            if (i == 0) {
                head = newlist;
            } else {
                tail.next = newlist;
                tail = newhead;
            }
        }
        //��������ʣ��Ĳ���
        while (cur != null) {
            tail.next = cur;
            tail = tail.next;
            cur = cur.next;
        }
        //����ͷ�ڵ�
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
