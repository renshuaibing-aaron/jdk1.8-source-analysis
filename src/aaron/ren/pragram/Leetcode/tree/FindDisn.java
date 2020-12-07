package aaron.ren.pragram.Leetcode.tree;

import java.util.ArrayList;

/**
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 */
public class FindDisn {

    public ArrayList<ArrayList<Integer>> FindPath(Node root, int target) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();

        if(root == null) return result;
        list.add(root.key);

        F(root, target, root.key, result, list);

        return result;
    }


    /**
     * result存储各条成功路径，list存储本条成功路径
     */
    public static void F(Node root, int target, int now, ArrayList<ArrayList<Integer>> result, ArrayList<Integer>list){

        //如果root是叶子，进行该路径之和的判断
        if(root.left==null && root.right==null){
            if(target == now){
                result.add((ArrayList<Integer>)list.clone());
            }
        }

        //将左右孩子分别放入路径继续
        else{

            if(root.left!=null){
                list.add(root.left.key);
                F(root.left, target, now + root.left.key, result,list);
                list.remove(list.size()-1);
            }

            if(root.right!=null){
                list.add(root.right.key);
                F(root.right, target, now + root.right.key, result, list);
                list.remove(list.size()-1);
            }
        }
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
