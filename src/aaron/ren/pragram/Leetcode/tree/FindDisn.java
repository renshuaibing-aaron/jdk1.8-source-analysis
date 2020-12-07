package aaron.ren.pragram.Leetcode.tree;

import java.util.ArrayList;

/**
 * ����һ�Ŷ�������һ����������ӡ���������н��ֵ�ĺ�Ϊ��������������·����·������Ϊ�����ĸ���㿪ʼ����һֱ��Ҷ����������Ľ���γ�һ��·����
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
     * result�洢�����ɹ�·����list�洢�����ɹ�·��
     */
    public static void F(Node root, int target, int now, ArrayList<ArrayList<Integer>> result, ArrayList<Integer>list){

        //���root��Ҷ�ӣ����и�·��֮�͵��ж�
        if(root.left==null && root.right==null){
            if(target == now){
                result.add((ArrayList<Integer>)list.clone());
            }
        }

        //�����Һ��ӷֱ����·������
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
        //���������߶ȣ���ײ��㵽��ǰ�����룩�������������߶�
        int lHeight, rHeight, height;
        int key;

        public Node(int i) {
            this.key = i;
        }

    }
}
