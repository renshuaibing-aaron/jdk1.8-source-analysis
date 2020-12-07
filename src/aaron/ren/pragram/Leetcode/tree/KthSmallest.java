package aaron.ren.pragram.Leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中第k小的数
 */
public class KthSmallest {

    public int kthSmallest(TreeNode root, int k) {

        List<Integer> list=new ArrayList<Integer>();
        inorder(list,root);

        return list.get(k-1);
    }

    void inorder(List<Integer> list,TreeNode root) {

        if (root == null) {
            return;
        }

        inorder(list, root.left);
        list.add(root.val);
        inorder(list, root.right);

    }
    public static class TreeNode<Integer>{
        java.lang.Integer val;
        TreeNode<java.lang.Integer> left;
        TreeNode <java.lang.Integer> right;
        public TreeNode(java.lang.Integer val) {
            super();
            this.val = val;
        }
    }
}
