package aaron.ren.pragram.Leetcode.tree;

/**
 * 有序数组构建平衡二叉树
 * 有序数组构建AVL树
 */
public class CreatAVL {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        TreeNode root = getAVLTree(nums);
        System.out.println(root.val);
    }

    public static TreeNode getAVLTree(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        return getAVLTree(nums, 0, nums.length - 1);
    }

    public static TreeNode getAVLTree(int[] nums, int start, int end) {
        TreeNode root = null;
        if (start <= end) {
            int mid = (start + end) / 2;
            root = new TreeNode(nums[mid]);
            root.left = getAVLTree(nums, start, mid - 1);
            root.right = getAVLTree(nums, mid + 1, end);
        }
        return root;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

}
