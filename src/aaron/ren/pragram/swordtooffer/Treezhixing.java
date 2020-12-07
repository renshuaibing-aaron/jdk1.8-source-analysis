package aaron.ren.pragram.swordtooffer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 打印树的之形式
 */
public class Treezhixing {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        /*打印二叉树。之字的话。那么我们可以利用两个栈，一个用来存奇数行（先右子树再左子树）
          一个用来存放偶数行（先左子树再右子树出站就是右往左）
        ，*/
        int layer = 1;
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        stack1.push(pRoot);
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>();
        while(!stack1.isEmpty() || !stack2.isEmpty()){
            if(layer % 2 == 1){
                ArrayList<Integer> list = new ArrayList<Integer>();
                while(!stack1.isEmpty()){
                    TreeNode node = stack1.pop();
                    if(node != null){
                        list.add(node.val);
                        System.out.print(node.val + " ");
                        stack2.push(node.left);//因为放这个左节点是因为之后下一层是偶数层，需要从右往左
                        stack2.push(node.right);
                    }
                }
                if(!list.isEmpty()){
                    lists.add(list);
                    layer ++;
                    System.out.println();
                }
            }else{
                ArrayList<Integer> list = new ArrayList<Integer>();
                while(!stack2.isEmpty()){
                    TreeNode node = stack2.pop();
                    if(node != null){
                        list.add(node.val);
                        System.out.print(node.val + " ");
                        stack1.push(node.right);
                        stack1.push(node.left);
                    }
                }
                if(!list.isEmpty()){
                    lists.add(list);
                    layer ++;
                    System.out.println();
                }
            }
        }
        return lists;

    }

    private static class TreeNode {
        int val;
        TreeNode right;
        TreeNode left;

        TreeNode(int element) {
            this.val = element;
        }
    }
}
