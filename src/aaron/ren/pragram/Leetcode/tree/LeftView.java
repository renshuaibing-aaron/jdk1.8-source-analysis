package aaron.ren.pragram.Leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * 二叉树的左（右）视图即：以从上到下的顺序，输出一颗二叉树每一层最左（右）端的节点，结果就是其左（右）视图。
 */
public class LeftView {


    public static List<String> solution(Node node){
        if(null == node){
            return Collections.emptyList();
        }

        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        pre(node, map, 1);
        List<String> result = new ArrayList<String>();
        for(Integer key : map.keySet()){
            result.add(map.get(key));
        }
        return result;
    }


    public static void pre(Node node, TreeMap<Integer,String> map, int level) {
        if (null == node) {
            return;
        }

        if (!map.containsKey(level)) {
            map.put(level, (String) node.data);
        }
        pre(node.lc, map, level + 1);
        pre(node.rc, map, level + 1);
    }

    public static class Node<String>{
        String data;
        Node  lc;
        Node   rc;
        public Node(String val) {
            super();
            this.data = val;
        }
    }
}
