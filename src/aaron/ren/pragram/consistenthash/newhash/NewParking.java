package aaron.ren.pragram.consistenthash.newhash;

import aaron.ren.pragram.consistenthash.bean.Car;
import aaron.ren.pragram.consistenthash.bean.Garage;

import java.util.SortedMap;
import java.util.TreeMap;

public class NewParking {
    /**
     * 按照 键 排序
     */
    TreeMap<Integer, Garage> nodes = new TreeMap<>();

    void addNode(Garage node) {
        //车库入队列
        nodes.put(node.hashCode(), node);
    }

    void put(Car obj) {
        //先利用自己的hash算法找 找到车库 便入库 找不到 寻找最近的入库即可
        int objHashcode = obj.hashCode();
        Garage node = nodes.get(objHashcode);
        if (node != null) {
            node.putObj(obj);
            return;
        }

        // 找到比给定 key 大的集合 一致性hash算法 如何找到合适的车库 不再使用之前的算法
        SortedMap<Integer, Garage> tailMap = nodes.tailMap(objHashcode);
        // 找到最小的节点
        int nodeHashcode = tailMap.isEmpty() ? nodes.firstKey() : tailMap.firstKey();
        nodes.get(nodeHashcode).putObj(obj);

    }

    Car get(Car obj) {
        Garage node = nodes.get(obj.hashCode());
        if (node != null) {
            return node.getObj(obj);
        }

        // 找到比给定 key 大的集合
        SortedMap<Integer, Garage> tailMap = nodes.tailMap(obj.hashCode());
        // 找到最小的节点
        int nodeHashcode = tailMap.isEmpty() ? nodes.firstKey() : tailMap.firstKey();
        return nodes.get(nodeHashcode).getObj(obj);
    }
}
