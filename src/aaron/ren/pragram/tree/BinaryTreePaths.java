package aaron.ren.pragram.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * https://blog.csdn.net/xiezongsheng1990/article/details/79574892
 * 打印根节点到叶子节点的所有路径
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        LinkedList<String> result = new LinkedList<String>();
        if(root == null) return result;
        getPath(root, result, root.val + "");
        return result;
    }
    public void getPath(TreeNode root, LinkedList<String> result, String str){
        if(root.left == null && root.right == null){
            result.add(str);
            return;
        }
        if(root.left != null)
            getPath(root.left, result, str + "->" + root.left.val);
        if(root.right != null)
            getPath(root.right, result, str + "->" + root.right.val);
    }

    //定义Node类，拥有元素值，节点名称，左孩子节点，右孩子节点，4个成员变量。
    private static   class TreeNode {
        int val;
        String name;
        TreeNode left;
        TreeNode right;

        public TreeNode(int element, String name) {
            this.val = element;
            this.name = name;
        }
    }
}
