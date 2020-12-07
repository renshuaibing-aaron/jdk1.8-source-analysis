package aaron.ren.pragram.Leetcode.linklist;

/**
 * 单链表的归并排序和快速排序
 */
public class ListSort {



    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
         mergeSort(head);

        // 没有条件，创造条件。自己添加头节点，最后返回时去掉即可。
        ListNode newHead=new ListNode(-1);
        newHead.next=head;
        return quickSort(newHead,null);
    }
    // 带头结点的链表快速排序
    private ListNode quickSort(ListNode head,ListNode end){
        if (head==end||head.next==end||head.next.next==end) {
            return head;
        }
        // 将小于划分点的值存储在临时链表中
        ListNode tmpHead=new ListNode(-1);
        // partition为划分点，p为链表指针，tp为临时链表指针
        ListNode partition=head.next,p=partition,tp=tmpHead;
        // 将小于划分点的结点放到临时链表中
        while (p.next!=end){
            if (p.next.val<partition.val){
                tp.next=p.next;
                tp=tp.next;
                p.next=p.next.next;
            }else {
                p=p.next;
            }
        }
        // 合并临时链表和原链表，将原链表接到临时链表后面即可
        tp.next=head.next;
        // 将临时链表插回原链表，注意是插回！（不做这一步在对右半部分处理时就断链了）
        head.next=tmpHead.next;
        quickSort(head,partition);
        quickSort(partition,end);
        // 题目要求不带头节点，返回结果时去除
        return head.next;
    }
    private static class ListNode {
        int val;
        ListNode next;
        ListNode( int element) {
            this.val = element;
        }
    }
    // 归并排序
    private ListNode mergeSort(ListNode head){
        // 如果没有结点/只有一个结点，无需排序，直接返回
        if (head==null||head.next==null) return head;
        // 快慢指针找出中位点
        ListNode slowp=head,fastp=head.next.next,l,r;
        while (fastp!=null&&fastp.next!=null){
            slowp=slowp.next;
            fastp=fastp.next.next;
        }
        // 对右半部分进行归并排序
        r=mergeSort(slowp.next);
        // 链表判断结束的标志：末尾节点.next==null
        slowp.next=null;
        // 对左半部分进行归并排序
        l=mergeSort(head);
        return mergeList(l,r);
    }
    // 合并链表
    private ListNode mergeList(ListNode l,ListNode r){
        // 临时头节点
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
