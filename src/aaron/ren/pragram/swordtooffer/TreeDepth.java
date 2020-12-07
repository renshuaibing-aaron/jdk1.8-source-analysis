package aaron.ren.pragram.swordtooffer;

/**
 * 输入一棵二叉树，求该树的深度。
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *       	    8
 *       	   /  \
 *      	  6   10
 *      	 / \
 *      	5  7
 */
public class TreeDepth {

    public int TreeDepth(TreeNode root) {
    //求树的深度 =左右子树深度的最大值+1
        int res=0 ;
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){
            return 1;
        }
        return Math.max(TreeDepth(root.left),TreeDepth(root.right)) +1;

    }








     private static class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;

     public TreeNode(int val) {
     this.val = val;

     }

     }
}
