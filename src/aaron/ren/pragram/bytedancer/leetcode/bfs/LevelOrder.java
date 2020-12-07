package aaron.ren.pragram.bytedancer.leetcode.bfs;

/**
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {

    public static void main(String[] args) {

    }
    /**
     * 这里的和普通层序遍历的区别
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
   if(root==null){return null;

   }
   Queue<TreeNode> queue=new LinkedList<>();
   List<List<Integer>>  res=new ArrayList<>();
   if(root!=null){
       queue.add(root); //这里为什么是add
   }
   while(!queue.isEmpty()){
       List<Integer> tmp=new ArrayList<>();
       int size = queue.size();
       for(int i=0;i<size;i++){
           TreeNode poll = queue.poll();
           tmp.add(poll.val);
         if(poll.left!=null){
             queue.add(poll.left);
         }
         if(poll.right!=null){
             queue.add(poll.right);
         }

       }
       res.add(tmp);

   }

   return res;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) {
            queue.add(root);
        }
        while(!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(tmp);
        }
        return res;
    }

      public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
          TreeNode(int x) { val = x; }
     }
}
