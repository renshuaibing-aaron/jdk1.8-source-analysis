package aaron.ren.pragram.Leetcode.tree;

/**
 * 判断二叉树是不是镜像的
 * 算法思想是：首先判断这棵树是否为空树，如果空树则直接返回true
 * 如果不为空，则在进行分类：case1：节点的左右子树为空，则直接返回true
 * case2：节点的左右子树有一个为空，则直接返回false
 * case3：节点的左右子树均不为空，则判断节点的左右子节点的值是否相等并且判断左节点的子左节点和右节点的右子节点是否对称还有左节点的右子节点和右节点的左子节点是否对称
 */
public class CheckTreeMirror {
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
       TreeNode left, right;
        int val;

        public TreeNode(int i) {
            this.val = i;
        }
    }
}
