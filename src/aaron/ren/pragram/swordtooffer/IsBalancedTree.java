package aaron.ren.pragram.swordtooffer;

/**
 * ����һ�ö��������жϸö������Ƿ���ƽ���������
 *
 * ���������ֻ��Ҫ������ƽ���ԣ�����Ҫ�������ǲ������������
 *       	     1
 *       	   /   \
 *      	  2    3
 *      	 / \    \
 *      	4  5     6
 *        	        /
 *        	        7
 */

//{1,2,3,4,5,#,6,#,#,7}
public class IsBalancedTree {

    public static void main(String[] args) {
        TreeNode node1=new TreeNode(1);
        TreeNode node2=new TreeNode(2);
        TreeNode node3=new TreeNode(3);
        TreeNode node4=new TreeNode(4);
        TreeNode node5=new TreeNode(5);
        TreeNode node6=new TreeNode(6);
        TreeNode node7=new TreeNode(7);

        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.right=node6;
        node6.left=node7;
        System.out.println(IsBalanced_Solution(node1));
    }

    public static  boolean IsBalanced_Solution(TreeNode root) {

        if(root==null) return true;
        // �����������ĸ߶�
        int leftLength = treeLength(root.left);

        int rightLength= treeLength(root.right);
        System.out.println(leftLength);
        System.out.println(rightLength);
        return Math.abs(leftLength-rightLength)<=1;

    }


    public static int treeLength(TreeNode root){
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){
            return  1;
        }

        return Math.max(treeLength(root.left),treeLength(root.right))+1;
    }


    public  static  int TreeDepth2(TreeNode root) {
        //��������� =����������ȵ����ֵ+1
        int res=0 ;
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){
            return 1;
        }
        return Math.max(TreeDepth2(root.left),TreeDepth2(root.right)) +1;

    }


    private static class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;

        TreeNode(int element) {
            this.val = element;
        }
    }
}
