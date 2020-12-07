package aaron.ren.pragram.Leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * ���ı����㷨
 *       1
 *     /   \
 *    2     3
 *  /  \     \
 * 4    5     6
 *  \
 *   7
 *
 *   todo ���ķǵݹ����
 */
public class TreeTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.right = new TreeNode(7);
        levelorderTraversal(root);

    }

    /**
     * ����������� �ݹ鷽��  ������
     * 1 2 4 7 5   3 6
     *
     * @param root
     * @return
     */
    public static void preorderTraversal(TreeNode root) {

        if (root != null) {
            System.out.println(root.key);
        }
        if (root.left != null) {
            preorderTraversal(root.left);
        }
        if (root.right != null) {
            preorderTraversal(root.right);
        }

    }

    public void preOrder(TreeNode Root) {
        if (Root == null) {
            System.out.println("����");
            return;
        }
        TreeNode tmp = Root;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(tmp);  //���ڵ���ջ
        while (!s.empty()) {
            //1.���ʸ��ڵ�
            TreeNode p = s.pop();
            System.out.print(p.key + " ");
            //2.������ڵ�����Һ��ӣ����Һ�����ջ
            if (p.right != null) {
                s.push(p.right);
            }
            //3.������ڵ�������ӣ���������ջ
            if (p.left != null) {
                s.push(p.left);
            }
        }
        System.out.println();
    }

        /**
         * ����������� �ݹ鷽��  �����
         *
         * @param root
         * @return
         */
    public static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        } else {
            inorderTraversal(root.left);
            System.out.println(root.key);
            inorderTraversal(root.right);
        }
    }

    /**
     * ���ĺ������ �ݹ鷽��  ���Ҹ�
     *
     * @param root
     * @return
     */
    public static void postorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        } else {
            inorderTraversal(root.left);
            inorderTraversal(root.right);
            System.out.println(root.key);
        }
    }

    /**
     * ���Ĳ������
     *
     * @param root
     * @return
     */
    public static void levelorderTraversal(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>() ;

        if (root != null) {
            queue.offer(root);
        }
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.key);
            if(node.left!=null){
                queue.offer(node.left);  //ע������ط� �ŵ��� ���ж���������
            }
            if(node.right!=null){
                queue.offer(node.right);
            }

        }
    }
    private static class TreeNode {
        TreeNode left, right;
        Integer key;

        public TreeNode(Integer i) {
            this.key = i;
        }
    }
}
