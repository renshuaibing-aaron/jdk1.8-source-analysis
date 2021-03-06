package aaron.ren.pragram.Leetcode.tree;

//将二叉搜索树转化为有序的双向链表
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

    //将二叉搜索树转化成双向链表,返回链表的头结点
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
     * 采用中序遍历思想将二叉搜索树的结点转化为双向链表的结点
     * @param root 从二叉搜索树的根开始转换
     * @param lastNode 当前链表的最后一个结点
     * @return 链表的最后一个结点
     */
    private BinaryNode convertNode(BinaryNode root, BinaryNode lastNode){
        if(root == null)
            return null;

        BinaryNode currentNode = root;//当前待转换子树的根结点
        if(root.left != null)
            lastNode = convertNode(root.left, lastNode);//向根的左子树转换(类似于中序遍历中左子树遍历)

        currentNode.left = lastNode;

        if(lastNode != null)
            lastNode.right = currentNode;
        lastNode = currentNode;//将currentNode加入到链表中

        if(root.right != null)
            lastNode = convertNode(root.right, lastNode);//往根的右子树转换(类似于中序遍历中右子树遍历)

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