package aaron.ren.pragram.Leetcode.tree;

/**
 * 二叉树的最远距离？
 * 二叉树最长路径（代码）
 *       1
 *     /  \
 *    2    3
 *   /\   /\
 *  4  5 6  7
 *        \
 *         8
 */
public class FindMaxLen {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);

        int maxLen = findMaxLen(root);
        System.out.println(maxLen);
    }

    public static int findMaxLen(Node root) {
        int lmaxLen, rmaxLen;
        if (root == null) {
            return 0;
        }
        lmaxLen = findMaxLen(root.left);
        rmaxLen = findMaxLen(root.right);
        if (root.left == null) {
            root.lHeight = 0;
        } else {
            root.lHeight = root.left.height + 1;
        }
        if (root.right == null) {
            root.rHeight = 0;
        } else {
            root.rHeight = root.right.height + 1;
        }
        root.height = root.lHeight > root.rHeight ? root.lHeight : root.rHeight;

        return max(lmaxLen, rmaxLen, root.lHeight + root.rHeight);

        //求这棵树的左子树的高度
        //求这棵树的右子树高度
    }

    private static int max(int a, int b, int c) {
        int tmp = a > b ? a : b;
        int result = tmp > c ? tmp : c;
        return result;
    }

    private static class Node {
        Node left, right;
        //左右子树高度（最底层结点到当前结点距离）及左右子树最大高度
        int lHeight, rHeight, height;
        int key;

        public Node(int i) {
            this.key = i;
        }

    }
}
