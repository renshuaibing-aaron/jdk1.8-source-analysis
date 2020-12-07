package aaron.ren.pragram.swordtooffer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * ��ӡ����֮��ʽ
 */
public class Treezhixing {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        /*��ӡ��������֮�ֵĻ�����ô���ǿ�����������ջ��һ�������������У�������������������
          һ���������ż���У�������������������վ����������
        ��*/
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
                        stack2.push(node.left);//��Ϊ�������ڵ�����Ϊ֮����һ����ż���㣬��Ҫ��������
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
