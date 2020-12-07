package aaron.ren.pragram.Leetcode.tree;

/**
 * 由一个树的前序遍历 和中序遍历  求这个树
 */
public class FZtoTree {

    public Node reConstructBinaryTree(int [] pre,int [] in) {
        Node root=reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length-1);
        return root;
    }
    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private Node reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {

        if(startPre>endPre||startIn>endIn)
            return null;
        Node root=new Node(pre[startPre]);

        for(int i=startIn;i<=endIn;i++)
            if(in[i]==pre[startPre]){
                root.left=reConstructBinaryTree(pre,startPre+1,startPre+i-startIn,in,startIn,i-1);
                root.right=reConstructBinaryTree(pre,i-startIn+startPre+1,endPre,in,i+1,endIn);
                break;
            }

        return root;
    }
    private  static  class Node{
     Node left,right;
        int key;

        public Node(int i) {
            this.key = i;
        }
    }
}
