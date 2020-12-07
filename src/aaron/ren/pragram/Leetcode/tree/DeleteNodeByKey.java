package aaron.ren.pragram.Leetcode.tree;

/**
 * ɾ�������������е�ָ���ڵ�
 */
public class DeleteNodeByKey {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        TreeNode node = root;
        TreeNode pre = null;
        boolean isLeft = true;
        while (node != null && node.val != key) { //Ѱ��key
            pre = node;
            if (key < node.val) {
                node = node.left;
                isLeft = true;
            } else {
                node = node.right;
                isLeft = false;
            }
        }
        if (node == null) //û�ҵ���ֱ�ӷ���
            return root;
        if (node.right == null) { //������Ϊnull��ֱ�Ӱ�����������ýڵ�
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
            while (node.left != null) { //�ҵ��������������½ڵ�
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
