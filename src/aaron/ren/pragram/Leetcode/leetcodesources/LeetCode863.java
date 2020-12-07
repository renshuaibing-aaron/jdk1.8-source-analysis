package aaron.ren.pragram.Leetcode.leetcodesources;

import java.util.*;

/**
 * 给定一个二叉树（具有根结点?root），?一个目标结点?target?，和一个整数值 K 。
 *
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 */
public class LeetCode863 {

    // 用map记录每个节点的父节点
    private Map<TreeNode, TreeNode> parents = new HashMap<>();

    private Set<TreeNode> used = new HashSet<>();

    private TreeNode targetNode;

    // 找到目标节点后以目标节点为开始位置向三个方向蔓延
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        find(root, null, target);
        List<Integer> res = new LinkedList<>();
        dfs(targetNode, res, K);
        return res;
    }

    private void find(TreeNode root, TreeNode parent, TreeNode target) {
        if (null == root) {
            return;
        }
        if (root.val == target.val) {
            targetNode = root;
        }
        parents.put(root, parent);
        find(root.left, root, target);
        find(root.right, root, target);
    }

    private void dfs(TreeNode root, List<Integer> collector, int distance) {
        if (root != null && !used.contains(root)) {
            // 标记为已访问
            used.add(root);
            if (distance <= 0) {
                collector.add(root.val);
                return;
            }
            dfs(root.left, collector, distance - 1);
            dfs(root.right, collector, distance - 1);
            dfs(parents.get(root), collector, distance - 1);
        }
    }

    private static class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int i) {
            this.val = i;
        }
    }

}