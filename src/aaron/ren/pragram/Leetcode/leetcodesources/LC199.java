package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ����һ�ö������������Լ�վ�������Ҳ࣬���մӶ������ײ���˳�򣬷��ش��Ҳ����ܿ����Ľڵ�ֵ��
 */
public class LC199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {  //����ǰ������һ���ڵ�������б�
                    res.add(node.val);
                }
            }
        }
        return res;
    }
    private static class TreeNode {
        TreeNode left, right;
        //���������߶ȣ���ײ��㵽��ǰ�����룩�������������߶�
        int lHeight, rHeight, height;
        int val;

        public TreeNode(int i) {
            this.val = i;
        }

    }
}
