package aaron.ren.pragram.Leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 找出二叉树每层的最大值节点
 */
public class FindMaxNodeInLevel {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.val = data;
        }
    }

    //找出二叉树每层的最大值节点
    public static void findMaxNodeInLevel(TreeNode pRoot) {
        ArrayList<Integer> resultList = new ArrayList<>();
        Queue<TreeNode> layer = new LinkedList<TreeNode>();
        ArrayList<Integer> layerList = new ArrayList<Integer>();
        layer.add(pRoot);
        int start = 0, end = 1;
        while (!layer.isEmpty()) {
            TreeNode cur = layer.remove();
            layerList.add(cur.val);
            start++;
            if (cur.left != null) {
                layer.add(cur.left);
            }
            if (cur.right != null) {
                layer.add(cur.right);
            }
            if (start == end) {
                end = layer.size();
                start = 0;
                resultList.add(findMaxNode(layerList));
                layerList = new ArrayList<Integer>();
            }
        }
        for (Integer i : resultList) {
            System.out.println(i);
        }
    }

    public static int findMaxNode(ArrayList<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (Integer i : list) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);
        findMaxNodeInLevel(head);
    }
}
