package aaron.ren.pragram.bytedancer.leetcode.linklist;

public class Demo {

    public static void main(String[] args) {

        ListNode node1=new ListNode(4);
        ListNode node2=new ListNode(5);
        ListNode node3=new ListNode(5);
        ListNode node4=new ListNode(9);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;

        ListNode listNode = reverseList(node1);
        while(listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }


    }

    public  static  ListNode deleteNode2(ListNode head, int val) {
        if(head == null){
            return null;
        }
        ListNode temp = head;
        if(head.val == val){
            return head.next;
        }

        while(head.next != null){
            if(head.next != null && head.next.val == val){
                head.next = head.next.next;
                break;
            }
            head = head.next;
        }
        return temp;
    }

  /*  输入: 1->2->3->4->5->NULL
    输出: 5->4->3->2->1->NULL*/
  public static  ListNode reverseList(ListNode head) {

      ListNode cur = head;
      ListNode lpr1 = new ListNode(-1);
      ListNode lpr2 = null;
      while (cur != null) {
          System.out.println(cur.val);
          lpr2 = cur;
          lpr2.next = lpr1;
          lpr1 = lpr2;
          cur = cur.next;
      }
      return head;
  }

    public ListNode middleNode(ListNode head) {

      int length=0;
      ListNode first=head;
      while(first!=null){
          length++;
          first=first.next;
      }
      int step=  length/2;
      while(step>0){
          head=head.next;
          step--;
      }
      return  head;

    }

    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;

    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int element) {
            this.val = element;
        }
    }





}

