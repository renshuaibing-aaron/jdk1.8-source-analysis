package aaron.ren.pragram.Leetcode.linklist;

/**
 * 判断单链表是否有环
 *设置快慢指针 慢的可以追上快的 就说明有环
 *
 * 1-2-3-4-5-6
 *     \    /
 *      7-8
 */
public class CheckCircle {
    public static void main(String[] args) {
      ListNode node1=new ListNode(1);
      ListNode node2=new  ListNode(2);
      ListNode node3=new ListNode(3);
      ListNode node4=new ListNode(4);
      ListNode node5=new ListNode(5);
      ListNode node6=new  ListNode(6);
      ListNode node7=new ListNode(7);
      ListNode node8=new ListNode(8);

      node1.next=node2;
      node2.next=node3;
      node3.next=node4;
      node4.next=node5;
      node5.next=node6;
      node6.next=node8;
      node8.next=node7;
      node7.next=node3;

        System.out.println(checkCircle(node1));

    }

    public static boolean checkCircle(ListNode head) {
        if(head==null||head!=head.next.next){
            return  false;
        }
        boolean result = false;
        //设置快慢两个指针 慢的追上快的 说明有环
        ListNode slowlpr = head;
        ListNode fasterlpr = head;

        while (fasterlpr != null) {
            slowlpr = slowlpr.next;
            fasterlpr = fasterlpr.next.next;
            if (slowlpr == fasterlpr) {
                result = true;
                break;
            }

        }
        return result;

    }


    private static  class  ListNode{
        private  int val;
        private ListNode next;
        public  ListNode(int val){
            this.val=val;
        }
    }
}
