package aaron.ren.pragram.Leetcode.linklist;

/**
 * ������Ĺ鲢����Ϳ�������
 */
public class ListSort {



    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
         mergeSort(head);

        // û�������������������Լ����ͷ�ڵ㣬��󷵻�ʱȥ�����ɡ�
        ListNode newHead=new ListNode(-1);
        newHead.next=head;
        return quickSort(newHead,null);
    }
    // ��ͷ���������������
    private ListNode quickSort(ListNode head,ListNode end){
        if (head==end||head.next==end||head.next.next==end) {
            return head;
        }
        // ��С�ڻ��ֵ��ֵ�洢����ʱ������
        ListNode tmpHead=new ListNode(-1);
        // partitionΪ���ֵ㣬pΪ����ָ�룬tpΪ��ʱ����ָ��
        ListNode partition=head.next,p=partition,tp=tmpHead;
        // ��С�ڻ��ֵ�Ľ��ŵ���ʱ������
        while (p.next!=end){
            if (p.next.val<partition.val){
                tp.next=p.next;
                tp=tp.next;
                p.next=p.next.next;
            }else {
                p=p.next;
            }
        }
        // �ϲ���ʱ�����ԭ������ԭ����ӵ���ʱ������漴��
        tp.next=head.next;
        // ����ʱ������ԭ����ע���ǲ�أ���������һ���ڶ��Ұ벿�ִ���ʱ�Ͷ����ˣ�
        head.next=tmpHead.next;
        quickSort(head,partition);
        quickSort(partition,end);
        // ��ĿҪ�󲻴�ͷ�ڵ㣬���ؽ��ʱȥ��
        return head.next;
    }
    private static class ListNode {
        int val;
        ListNode next;
        ListNode( int element) {
            this.val = element;
        }
    }
    // �鲢����
    private ListNode mergeSort(ListNode head){
        // ���û�н��/ֻ��һ����㣬��������ֱ�ӷ���
        if (head==null||head.next==null) return head;
        // ����ָ���ҳ���λ��
        ListNode slowp=head,fastp=head.next.next,l,r;
        while (fastp!=null&&fastp.next!=null){
            slowp=slowp.next;
            fastp=fastp.next.next;
        }
        // ���Ұ벿�ֽ��й鲢����
        r=mergeSort(slowp.next);
        // �����жϽ����ı�־��ĩβ�ڵ�.next==null
        slowp.next=null;
        // ����벿�ֽ��й鲢����
        l=mergeSort(head);
        return mergeList(l,r);
    }
    // �ϲ�����
    private ListNode mergeList(ListNode l,ListNode r){
        // ��ʱͷ�ڵ�
        ListNode tmpHead=new ListNode(-1);
        ListNode p=tmpHead;
        while (l!=null&&r!=null){
            if (l.val<r.val){
                p.next=l;
                l=l.next;
            }else {
                p.next=r;
                r=r.next;
            }
            p=p.next;
        }
        p.next=l==null?r:l;
        return tmpHead.next;
    }
}
