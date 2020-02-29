package aaron.ren.collection.consistenthash.normalhash;

import aaron.ren.collection.consistenthash.bean.Car;
import aaron.ren.collection.consistenthash.bean.Garage;

public class OldMain {

    /**
     * 验证普通 hash 对于增减节点，原有会不会出现移动。
     */
    public static void main(String[] args) {

        OldParking nodeArray = new OldParking();

        Garage[] nodes = {
                new Garage("车库A"),
                new Garage("车库B"),
                new Garage("车库C")
        };

        for (Garage node : nodes) {
            nodeArray.addNode(node);
        }

        Car[] objs = {
                new Car("奥迪"),
                new Car("大众"),
                new Car("奔驰"),
                new Car("宝马"),
                new Car("保时捷")
        };

        for (Car obj : objs) {
            nodeArray.put(obj);
        }

        validate(nodeArray, objs);
    }

    private static void validate(OldParking nodeArray, Car[] objs) {
        for (Car obj : objs) {
            System.out.println(nodeArray.get(obj));
        }

        nodeArray.addNode(new Garage("车库D"));
        nodeArray.addNode(new Garage("车库E"));

        System.out.println("========== after  =============");

        for (Car obj : objs) {
            System.out.println(nodeArray.get(obj));
        }
    }
}
