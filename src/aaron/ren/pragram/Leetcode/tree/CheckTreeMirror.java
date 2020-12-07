package aaron.ren.pragram.Leetcode.tree;

/**
 * �ж϶������ǲ��Ǿ����
 * �㷨˼���ǣ������ж�������Ƿ�Ϊ���������������ֱ�ӷ���true
 * �����Ϊ�գ����ڽ��з��ࣺcase1���ڵ����������Ϊ�գ���ֱ�ӷ���true
 * case2���ڵ������������һ��Ϊ�գ���ֱ�ӷ���false
 * case3���ڵ��������������Ϊ�գ����жϽڵ�������ӽڵ��ֵ�Ƿ���Ȳ����ж���ڵ������ڵ���ҽڵ�����ӽڵ��Ƿ�Գƻ�����ڵ�����ӽڵ���ҽڵ�����ӽڵ��Ƿ�Գ�
 */
public class CheckTreeMirror {
    boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null) {
            return true;
        }

        return isEqual(pRoot.left, pRoot.right);
    }
    public static boolean isEqual(TreeNode pLeft, TreeNode pRight) {
        if(pLeft == null && pRight == null)		//��Ϊ��
        {
            return true;
        }

        if((pLeft == null && pRight != null)	//��һ��Ϊ��
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
