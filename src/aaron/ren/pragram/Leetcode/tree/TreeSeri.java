package aaron.ren.pragram.Leetcode.tree;

/**
 * 二叉树的序列化与反序列化
 */
public class TreeSeri {

    public class Solution {
        String Serialize(TreeNode root) {
            StringBuffer sb = new StringBuffer();
            if (root == null) {
                sb.append('#');
                sb.append(' ');
            } else {
                sb.append(root.key);
                sb.append(' ');
                String left = Serialize(root.left);
                String right = Serialize(root.right);
                sb.append(left);
                sb.append(right);
            }
            return sb.toString();
        }

        TreeNode Deserialize(String str) {
            String[] strs = str.split(" ");
            return Deserialize(strs);

        }

        int index = -1;

        TreeNode Deserialize(String[] strs) {
            index++;
            if (strs[index].equals("#") || index > (strs.length - 1)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(strs[index]));

            root.left = Deserialize(strs);

            root.right = Deserialize(strs);
            return root;
        }

    }

    private static class TreeNode {
        TreeNode left, right;
        int key;

        public TreeNode(int i) {
            this.key = i;
        }
    }
}
