package aaron.ren.pragram.Leetcode.tree;

//������������ת��Ϊ�����˫������
public class BST2List {

    private class BinaryNode implements Comparable<BinaryNode>{
        int ele;
        BinaryNode left;
        BinaryNode right;

        public BinaryNode(int ele) {
            this.ele = ele;
            left = right = null;
        }

        @Override
        public int compareTo(BinaryNode node) {
            return this.ele - node.ele;
        }

        @Override
        public String toString() {
            return this.ele + " ";
        }

    }

    private BinaryNode root;

    public void buildTree(int[] ele){
        for (int i : ele) {
            insert(i);
        }
    }
    private void insert(int ele){
        root = insert(root, ele);
    }
    private BinaryNode insert(BinaryNode root, int ele){
        if(root == null)
            return new BinaryNode(ele);
        if(root.ele > ele)
            root.left = insert(root.left, ele);
        else if(root.ele < ele)
            root.right = insert(root.right, ele);
        return root;
    }

    //������������ת����˫������,���������ͷ���
    public BinaryNode bstConvert2List(BinaryNode root){
        if(root == null)
            return null;

        BinaryNode lastNode = null;
        lastNode = convertNode(root, lastNode);

        BinaryNode head = null;
        while(lastNode != null)
        {
            head = lastNode;
            lastNode = lastNode.left;
        }
        return head;
    }
    /**
     * �����������˼�뽫�����������Ľ��ת��Ϊ˫������Ľ��
     * @param root �Ӷ����������ĸ���ʼת��
     * @param lastNode ��ǰ��������һ�����
     * @return ��������һ�����
     */
    private BinaryNode convertNode(BinaryNode root, BinaryNode lastNode){
        if(root == null)
            return null;

        BinaryNode currentNode = root;//��ǰ��ת�������ĸ����
        if(root.left != null)
            lastNode = convertNode(root.left, lastNode);//�����������ת��(�������������������������)

        currentNode.left = lastNode;

        if(lastNode != null)
            lastNode.right = currentNode;
        lastNode = currentNode;//��currentNode���뵽������

        if(root.right != null)
            lastNode = convertNode(root.right, lastNode);//������������ת��(�������������������������)

        return lastNode;
    }

    public void printList(BinaryNode head){
        if(head == null)
            return;
        BinaryNode current = head;
        while(current != null)
        {
            System.out.print(current);
            current = current.right;
        }
    }

    //hapjin test
    public static void main(String[] args) {
        int[] eles = {10,6,14,4,8,12,16};
        BST2List obj = new BST2List();
        obj.buildTree(eles);

        BinaryNode head = obj.bstConvert2List(obj.root);
        obj.printList(head);
    }
}