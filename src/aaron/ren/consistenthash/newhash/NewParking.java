package aaron.ren.consistenthash.newhash;

import aaron.ren.consistenthash.bean.Car;
import aaron.ren.consistenthash.bean.Garage;

import java.util.SortedMap;
import java.util.TreeMap;

public class NewParking {
    /**
     * ���� �� ����
     */
    TreeMap<Integer, Garage> nodes = new TreeMap<>();

    void addNode(Garage node) {
        //���������
        nodes.put(node.hashCode(), node);
    }

    void put(Car obj) {
        //�������Լ���hash�㷨�� �ҵ����� ����� �Ҳ��� Ѱ���������⼴��
        int objHashcode = obj.hashCode();
        Garage node = nodes.get(objHashcode);
        if (node != null) {
            node.putObj(obj);
            return;
        }

        // �ҵ��ȸ��� key ��ļ��� һ����hash�㷨 ����ҵ����ʵĳ��� ����ʹ��֮ǰ���㷨
        SortedMap<Integer, Garage> tailMap = nodes.tailMap(objHashcode);
        // �ҵ���С�Ľڵ�
        int nodeHashcode = tailMap.isEmpty() ? nodes.firstKey() : tailMap.firstKey();
        nodes.get(nodeHashcode).putObj(obj);

    }

    Car get(Car obj) {
        Garage node = nodes.get(obj.hashCode());
        if (node != null) {
            return node.getObj(obj);
        }

        // �ҵ��ȸ��� key ��ļ���
        SortedMap<Integer, Garage> tailMap = nodes.tailMap(obj.hashCode());
        // �ҵ���С�Ľڵ�
        int nodeHashcode = tailMap.isEmpty() ? nodes.firstKey() : tailMap.firstKey();
        return nodes.get(nodeHashcode).getObj(obj);
    }
}
