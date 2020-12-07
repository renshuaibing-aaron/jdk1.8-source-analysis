package aaron.ren.pragram.Leetcode.leetcodesources;

/**
 * ����һ�ö�����������Ҫ��������ֱ�����ȡ�һ�ö�������ֱ�������������������·�������е����ֵ������·�����ܴ���Ҳ���ܲ���������㡣
 *
 * ʾ�� :
 * ����������
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 */
public class LC543 {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 0;
        }
        int leftSize = root.left == null? 0: dfs(root.left) + 1;
        int rightSize = root.right == null? 0: dfs(root.right) + 1;
        max = Math.max(max, leftSize + rightSize);
        return Math.max(leftSize, rightSize);
    }

    private static class TreeNode {
  TreeNode left, right;
        Integer key;

        public TreeNode(Integer i) {
            this.key = i;
        }
    }
}
