package aaron.ren.pragram.Leetcode.tree;

/**
 * 删除二叉搜索树中的指定节点
 */
public class DeleteNodeByKey {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        TreeNode node = root;
        TreeNode pre = null;
        boolean isLeft = true;
        while (node != null && node.val != key) { //寻找key
            pre = node;
            if (key < node.val) {
                node = node.left;
                isLeft = true;
            } else {
                node = node.right;
                isLeft = false;
            }
        }
        if (node == null) //没找到，直接返回
            return root;
        if (node.right == null) { //右子树为null，直接把左子树替代该节点
            if (pre == null) return node.left;
            else {
                if (isLeft) pre.left = node.left;
                else pre.right = node.left;
                return root;
            }
        } else {  //node.right != null
            TreeNode l = node.left;
            TreeNode r = node.right;
            node = node.right;
            while (node.left != null) { //找到右子树的最左下节点
                node = node.left;
            }
            node.left = l;
            if (pre == null)
                return r;
            else {
                if (isLeft) pre.left = r;
                else pre.right = r;
                return root;
            }
        }
    }

    private static class TreeNode {
        TreeNode left, right;
        Integer val;

        public TreeNode(Integer i) {
            this.val = i;
        }
    }
}
