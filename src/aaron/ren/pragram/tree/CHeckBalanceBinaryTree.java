package aaron.ren.pragram.tree;

public class CHeckBalanceBinaryTree {
    // 创建结点内部类
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }
    /* (1): 左树是否平衡
     * (2): 右树是否平衡
     * (3): 左树的高度
     * (4): 右树的高度
     * (5): 比较两树高度
     * */
    public static class ReturnData{
        public Boolean isB ;
        public int h ;
        public ReturnData(Boolean isB , int h) {
            this.isB = isB;
            this.h = h;
        }
    }

    public static boolean isBalance(Node head) {
        return Process(head).isB;
    }
    public static ReturnData Process(Node head) {
        // 空树为平衡二叉树
        if(head == null) {
            return new ReturnData(true, 0);
        }
        // 判断左子树是否为平衡二叉树
        ReturnData leftData = Process(head.left);
        if(!leftData.isB) {
            return new ReturnData(false, 0);
        }
        // 判断右子树是否为平衡二叉树
        ReturnData rightData = Process(head.right);
        if(!rightData.isB) {
            return new ReturnData(false, 0);
        }
        // 判断两棵子树的高度差是否大于1，大于1就不是平衡二叉树
        if(Math.abs(leftData.h-rightData.h) > 1) {
            return new ReturnData(false, 0);
        }
        // 以上条件都满足，返回true。
        // 对于任意子树的高度== 其 max(左子树.h,右子树.h)+1
        return new ReturnData(true, Math.max(leftData.h, rightData.h)+1);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }

}
