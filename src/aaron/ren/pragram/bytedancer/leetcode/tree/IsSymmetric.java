package aaron.ren.pragram.bytedancer.leetcode.tree;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 */
public class IsSymmetric {




    boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null) {
            return true;
        }

        return isEqual(pRoot.left, pRoot.right);
    }
    public static boolean isEqual(TreeNode pLeft, TreeNode pRight) {
        if(pLeft == null && pRight == null)		//均为空
        {
            return true;
        }

        if((pLeft == null && pRight != null)	//有一个为空
                || (pLeft != null && pRight == null)) {
            return false;
        }

        if(pLeft.val == pRight.val) {
            return isEqual(pLeft.left, pRight.right) && isEqual(pLeft.right, pRight.left);
        }

        return false;

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
