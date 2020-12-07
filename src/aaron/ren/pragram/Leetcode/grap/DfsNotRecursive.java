package aaron.ren.pragram.Leetcode.grap;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DfsNotRecursive {
    public static <V> void dfsNotRecursive(TreeNode<V> tree) {
        if (tree != null) {
            //����֮������ Map ֻ��Ϊ�˱���ڵ����ȣ�
            //���û�����������Ը�Ϊ Stack<TreeNode<V>>
            Stack<Map<TreeNode<V>, Integer>> stack = new Stack<>();
            Map<TreeNode<V>, Integer> root = new HashMap<>();
            root.put(tree, 0);
            stack.push(root);
            while (!stack.isEmpty()) {
                Map<TreeNode<V>, Integer> item = stack.pop();
                TreeNode<V> node = item.keySet().iterator().next();
                int depth = item.get(node);
                //��ӡ�ڵ�ֵ�Լ����
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
