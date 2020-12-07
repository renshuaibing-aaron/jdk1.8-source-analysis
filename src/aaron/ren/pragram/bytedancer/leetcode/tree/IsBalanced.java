package aaron.ren.pragram.bytedancer.leetcode.tree;

/**
 * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *       1
 *      / \
 *     2   2
 *    / \
 *   3   3
 *  / \
 * 4   4
 * 返回 false 。
 */
public class IsBalanced {

    public boolean isBalanced(TreeNode root) {

        //使用递归
        if(root==null){
            return true;
        }
        int leftDeep=getDeep(root.left);
        int rightDeep=getDeep(root.right);
        if(Math.abs(leftDeep-rightDeep)>1){
            return false;
        }
       return isBalanced(root.left)&&isBalanced(root.right);
    }

    //求树的深度
    private int getDeep(TreeNode root) {
        if(root==null) {
            return 0;
        }
        return Math.max(getDeep(root.right),getDeep(root.left))+1;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
