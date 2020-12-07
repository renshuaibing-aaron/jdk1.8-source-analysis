package aaron.ren.pragram.Leetcode.grap;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class BfsNotRecursive {
    public static <V> void bfsNotRecursive(TreeNode<V> tree) {
        if (tree != null) {
            //跟上面一样，使用 Map 也只是为了保存树的深度，没这个需要可以不用 Map
            Queue<Map<TreeNode<V>, Integer>> queue = new ArrayDeque<>();
            Map<TreeNode<V>, Integer> root = new HashMap<>();
            root.put(tree, 0);
            queue.offer(root);
            while (!queue.isEmpty()) {
                Map<TreeNode<V>, Integer> itemMap = queue.poll();
                TreeNode<V> itemTreeNode = itemMap.keySet().iterator().next();
                int depth = itemMap.get(itemTreeNode);
                //打印节点值以及深度
                System.out.println(itemTreeNode.getValue().toString() + ",   " + depth);
                if (itemTreeNode.getChildList() != null &&
                        !itemTreeNode.getChildList().isEmpty()) {
                    for (TreeNode<V> child : itemTreeNode.getChildList()) {
                        Map<TreeNode<V>, Integer> map = new HashMap<>();
                        map.put(child, depth + 1);
                        queue.offer(map);
                    }
                }
            }
        }
    }
}
