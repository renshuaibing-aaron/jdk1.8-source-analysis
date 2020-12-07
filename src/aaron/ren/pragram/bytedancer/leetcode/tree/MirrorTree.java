package aaron.ren.pragram.bytedancer.leetcode.tree;

/**
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * 例如输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 */
public class MirrorTree {
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null) return root;

        TreeNode node=root.left;
        root.left= root.right;
        root.right=node;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
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
