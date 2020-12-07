package aaron.ren.pragram.Leetcode.tree;

import java.util.Stack;

/**
 * 树的非递归后续遍历
 */
public class PostBianli {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        TreeNode<Integer> l = new TreeNode<Integer>(2);
        TreeNode<Integer> r = new TreeNode<Integer>(4);
        TreeNode<Integer> ll = new TreeNode<Integer>(5);
        TreeNode<Integer> lr = new TreeNode<Integer>(3);
        root.left = l;
        root.right = r;
        l.left = ll;
        l.right = lr;
        postOrder(root);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void postOrder(TreeNode<Integer> root) {
        Stack<TreeNode> src = new Stack<TreeNode>();
        Stack<TreeNode> res = new Stack<TreeNode>();
        src.push(root);
        while(!src.isEmpty()){
            TreeNode<Integer> p = src.pop();
            res.push(p);
            if(p.left != null){
                src.push(p.left);
            }
            if(p.right != null){
                src.push(p.right);
            }
        }
        //输出最终后序遍历的结果
        while(!res.isEmpty()){
            System.out.print(res.pop().val + " ");
        }
    }

    public static class TreeNode<T>{
        T val;
        TreeNode<T> left;
        TreeNode <T> right;
        public TreeNode(T val) {
            super();
            this.val = val;
        }
    }

}
