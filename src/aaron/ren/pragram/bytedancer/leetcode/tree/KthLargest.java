package aaron.ren.pragram.bytedancer.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 */
public class KthLargest {


    public int kthLargest(TreeNode root, int k) {

        List<Integer> list=new ArrayList<>();
        inorderTraversal(root,list);
        Integer[] integers = (Integer[]) list.toArray(new Integer[list.size()]);
        return integers[integers.length-k];


    }

    private static  void inorderTraversal(TreeNode root, List list){

        if (root == null) {
            return;
        } else {
            inorderTraversal(root.left,list);
           list.add(root.val);
            inorderTraversal(root.right,list);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
