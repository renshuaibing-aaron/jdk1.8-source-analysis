package aaron.ren.pragram.Leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * �����������ң���ͼ�����Դ��ϵ��µ�˳�����һ�Ŷ�����ÿһ�������ң��˵Ľڵ㣬������������ң���ͼ��
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
