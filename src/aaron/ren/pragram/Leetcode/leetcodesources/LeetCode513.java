package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ����һ�������������������һ���ҵ�����ߵ�ֵ��
 */
public class LeetCode513 {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            root = queue.poll();
            if (root.right != null) {
                queue.offer(root.right);
            }
            if (root.left != null) {
                queue.offer(root.left);
            }
        }
        return root.val;
    }
    private static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int i) {
            this.val = i;
        }
    }
}
