package aaron.ren.pragram.Leetcode.tree;

/**
 * 如何判断一棵树是不是二叉搜索树
 */
public class IsBST {
    //中序遍历的方法实现
    boolean isBST(TreeNode root)
    {
     TreeNode  prev = null;
        if(root != null)
        {
            if(!isBST(root.left)) {
                return false;
            }
            if(prev != null && root.key < prev.key) {
                return false;
            }
            prev = root;
            return isBST(root.right);
        }
        return true;
    }
    private static class TreeNode {
      TreeNode left, right;
        int key;

        public TreeNode(int i) {
            this.key = i;
        }
    }
}
