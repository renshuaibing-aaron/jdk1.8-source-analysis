package aaron.ren.pragram.Leetcode.tree;

/**
 * �������������ڵ������������� LCA����
 * 1.��������һ�㶼����ʹ�õݹ���н�� �ؼ������ �ҵ�ͻ�ƿ�
 *
 *          1
 *       /    \
 *      2      3
 *     / \    /
 *    4   5  6
 */
public class TreeNodesDistance {

    //����node�ڵ���root�еĵڼ��㣬-1��ʾû����root�������ҵ�
    public static int findLevel(Node root, int node){
        if(root == null) {
            return -1;
        }
        if(root.key == node) {
            return 0;
        }
        //��������������
        int level = findLevel(root.left, node);
        //������û���ҵ�������������
        if(level == -1){
            level = findLevel(root.right, node);
        }
        if(level != -1) {
            return level+1;
        }
        return -1;
    }

    public static Node findLCA(Node root, int node1,int node2){
        if(root == null) {
            return null;
        }

        //�ҵ������ڵ��е�һ���ͷ���
        if(root.key == node1 || root.key == node2){
            return root;
        }

        //�ֱ��������������������ڵ�
        Node left_lca = findLCA(root.left, node1, node2);
        Node right_lca = findLCA(root.right, node1, node2);

        if(left_lca != null && right_lca != null){
            //��ʱ˵���������ڵ�϶��Ƿֱ������������У���ǰ�ڵ��ΪLCA
            return root;
        }
        return left_lca != null ? left_lca : right_lca;
    }

    public static int distanceNodes(Node root, int node1, int node2){
        Node lca = findLCA(root, node1, node2);
        int dis_lca = findLevel(root, lca.key);
        int dis1 = findLevel(root, node1);
        int dis2 = findLevel(root, node2);
        return dis1 + dis2 - 2*dis_lca;
    }

    public static void main(String args[]){
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);

        System.out.println("Dist(8,7) = " + distanceNodes(root, 8,7));
        System.out.println("Dist(8,3) = " + distanceNodes(root, 8,3));
        System.out.println("Dist(8,3) = " + distanceNodes(root, 8,2));
    }
    private  static  class Node{
        Node left,right;
        int key;

        public Node(int i) {
            this.key = i;
        }
    }
}
