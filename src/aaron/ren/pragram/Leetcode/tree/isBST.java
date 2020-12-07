package aaron.ren.pragram.Leetcode.tree;

/**
 * ����ж�һ�����ǲ��Ƕ���������
 */
public class IsBST {
    //��������ķ���ʵ��
    boolean isBST(TreeNode root)
    {
     TreeNode  prev = null;
        if(root != null)
        {
            if(!isBST(root.left)) {
                return false;
            }
            if(prev != null && root.key < prev.key) {
                return false;
            }
            prev = root;
            return isBST(root.right);
        }
        return true;
    }
    private static class TreeNode {
      TreeNode left, right;
        int key;

        public TreeNode(int i) {
            this.key = i;
        }
    }
}
