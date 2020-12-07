package aaron.ren.pragram.Leetcode.grap;

public class DFS {
    public static <V> void dfs(TreeNode<V> tree, int depth) {
        if (tree != null) {
            //��ӡ�ڵ�ֵ�Լ����
            System.out.println(tree.getValue().toString() + ",   " + depth);
            if (tree.getChildList() != null && !tree.getChildList().isEmpty()) {
                for (TreeNode<V> item : tree.getChildList()) {
                    dfs(item, depth + 1);
                }
            }
        }
    }
}
