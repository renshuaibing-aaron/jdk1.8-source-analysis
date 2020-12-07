package aaron.ren.pragram.Leetcode.tree;

public class GetTreeMirror {

    public void Mirror(TreeNode root) {
        root = doMirror(root);
    }

    /**
     * 利用递归求解
     * @param root
     * @return
     */
    private TreeNode doMirror(TreeNode root) {
        if(root == null) {
            return null;
        }

        root.left = doMirror(root.left);
        root.right = doMirror(root.right);

        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        return root;
    }
    private static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int i) {
            this.val = i;
        }
    }
}
