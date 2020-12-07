package aaron.ren.pragram.Leetcode.grap;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DfsNotRecursive {
    public static <V> void dfsNotRecursive(TreeNode<V> tree) {
        if (tree != null) {
            //次数之所以用 Map 只是为了保存节点的深度，
            //如果没有这个需求可以改为 Stack<TreeNode<V>>
            Stack<Map<TreeNode<V>, Integer>> stack = new Stack<>();
            Map<TreeNode<V>, Integer> root = new HashMap<>();
            root.put(tree, 0);
            stack.push(root);
            while (!stack.isEmpty()) {
                Map<TreeNode<V>, Integer> item = stack.pop();
                TreeNode<V> node = item.keySet().iterator().next();
                int depth = item.get(node);
                //打印节点值以及深度
                System.out.println(tree.getValue().toString() + ",   " + depth);
                if (node.getChildList() != null && !node.getChildList().isEmpty()) {
                    for (TreeNode<V> treeNode : node.getChildList()) {
                        Map<TreeNode<V>, Integer> map = new HashMap<>();
                        map.put(treeNode, depth + 1);
                        stack.push(map);
                    }
                }
            }
        }
    }
}
