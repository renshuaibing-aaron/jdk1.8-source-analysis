package aaron.ren.pragram.Leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一棵树是不是完全二叉树
 */
public class IsCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static boolean isCBT(Node head){
        if(head == null){
            return true;	//空树也是完全二叉树
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(head);
        boolean leaf = false;	//是否开启后续叶节点的检查
        Node l = null;
        Node r = null;
        while(!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;
            if((leaf&&(l!=null||r!=null))||(l==null&&r!=null)){
                return false;
            }
            if(l != null){
                queue.offer(l);
            }
            if(r != null){
                queue.offer(r);
            }else{
                leaf = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        System.out.println(isCBT(head));

    }
}
