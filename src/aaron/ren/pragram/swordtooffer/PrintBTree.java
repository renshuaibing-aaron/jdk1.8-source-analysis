package aaron.ren.pragram.swordtooffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ���ϵ��°����ӡ��������ͬһ����������������ÿһ�����һ�С�
 */
public class PrintBTree {

    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot)  {

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<ArrayList<Integer>>();
        if (pRoot==null) {
            return arrayLists;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                if (queue.peek().left!=null) {
                    queue.add(queue.peek().left);
                }
                if (queue.peek().right!=null) {
                    queue.add(queue.peek().right);
                }
                arrayList.add(queue.poll().val);
            }
            arrayLists.add(arrayList);
        }
        return arrayLists;
    }

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
