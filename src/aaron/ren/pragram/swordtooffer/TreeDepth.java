package aaron.ren.pragram.swordtooffer;

/**
 * ����һ�ö����������������ȡ�
 * �Ӹ���㵽Ҷ������ξ����Ľ�㣨������Ҷ��㣩�γ�����һ��·�����·���ĳ���Ϊ������ȡ�
 *       	    8
 *       	   /  \
 *      	  6   10
 *      	 / \
 *      	5  7
 */
public class TreeDepth {

    public int TreeDepth(TreeNode root) {
    //��������� =����������ȵ����ֵ+1
        int res=0 ;
        if(root==null){
            return 0;
        }
        if(root.left==null&&root.right==null){
            return 1;
        }
        return Math.max(TreeDepth(root.left),TreeDepth(root.right)) +1;

    }








     private static class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;

     public TreeNode(int val) {
     this.val = val;

     }

     }
}
