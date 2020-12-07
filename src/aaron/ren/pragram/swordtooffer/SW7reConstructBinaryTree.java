package aaron.ren.pragram.swordtooffer;

import java.util.Arrays;

/**
 * 根据树的前序 中序遍历结果 构建二叉树
 */
public class SW7reConstructBinaryTree {

    public  static  TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0) return null;

        int rootval = pre[0];
        if (pre.length == 1) {
            return new TreeNode(rootval);

        }
        TreeNode rootNode = new TreeNode(rootval);
        int rootIndex = 0;
        for (int i = 0; i < in.length; i++) {
            if (rootval == in[i]) {
                rootIndex = i;
                break;
            }
        }

        rootNode.left=reConstructBinaryTree(Arrays.copyOfRange(pre,1,rootIndex+1),Arrays.copyOfRange(in,0,rootIndex));
        rootNode.right=reConstructBinaryTree(Arrays.copyOfRange(pre,rootIndex+1,pre.length),Arrays.copyOfRange(in,rootIndex+1,in.length));

        return rootNode;

    }

    /*  Definition for binary tree*/
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
