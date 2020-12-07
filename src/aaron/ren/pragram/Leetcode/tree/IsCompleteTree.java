package aaron.ren.pragram.Leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IsCompleteTree {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        queue.offer(root);
        int n=1;
        boolean re=true;
        List<Integer> list=new ArrayList<Integer>();
        int tag=0;
        while(!queue.isEmpty()){
            for(int i=0;i<queue.size();i++){
                TreeNode node=queue.poll();
                if(node==null){
                    if(!queue.isEmpty()||queue.poll()!=null){
                        re=false;
                        break;
                    }
                }
                else{
                    if(node.left!=null||node.right!=null){
                        if(tag==1){
                            re=false;
                            break;
                        }
                        queue.offer(node.left);
                        queue.offer(node.right);
                    }
                    else{

                        tag=1;
                    }
                }

            }
            if(re==false)
                break;


        }

        return re;
    }

    public static class TreeNode<Integer>{
        java.lang.Integer val;
        TreeNode<java.lang.Integer> left;
        TreeNode <java.lang.Integer> right;
        public TreeNode(java.lang.Integer val) {
            super();
            this.val = val;
        }
    }
}
