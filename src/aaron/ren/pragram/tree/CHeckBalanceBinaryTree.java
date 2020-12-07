package aaron.ren.pragram.tree;

public class CHeckBalanceBinaryTree {
    // ��������ڲ���
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }
    /* (1): �����Ƿ�ƽ��
     * (2): �����Ƿ�ƽ��
     * (3): �����ĸ߶�
     * (4): �����ĸ߶�
     * (5): �Ƚ������߶�
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
        // ����Ϊƽ�������
        if(head == null) {
            return new ReturnData(true, 0);
        }
        // �ж��������Ƿ�Ϊƽ�������
        ReturnData leftData = Process(head.left);
        if(!leftData.isB) {
            return new ReturnData(false, 0);
        }
        // �ж��������Ƿ�Ϊƽ�������
        ReturnData rightData = Process(head.right);
        if(!rightData.isB) {
            return new ReturnData(false, 0);
        }
        // �ж����������ĸ߶Ȳ��Ƿ����1������1�Ͳ���ƽ�������
        if(Math.abs(leftData.h-rightData.h) > 1) {
            return new ReturnData(false, 0);
        }
        // �������������㣬����true��
        // �������������ĸ߶�== �� max(������.h,������.h)+1
        return new ReturnData(true, Math.max(leftData.h, rightData.h)+1);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }

}
