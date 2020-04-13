package aaron.ren.pragram.consistenthash.normalhash;

import aaron.ren.pragram.consistenthash.bean.Car;
import aaron.ren.pragram.consistenthash.bean.Garage;

public class OldMain {

    /**
     * ��֤��ͨ hash ���������ڵ㣬ԭ�л᲻������ƶ���
     */
    public static void main(String[] args) {

        OldParking nodeArray = new OldParking();

        Garage[] nodes = {
                new Garage("����A"),
                new Garage("����B"),
                new Garage("����C")
        };

        for (Garage node : nodes) {
            nodeArray.addNode(node);
        }

        Car[] objs = {
                new Car("�µ�"),
                new Car("����"),
                new Car("����"),
                new Car("����"),
                new Car("��ʱ��")
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

        nodeArray.addNode(new Garage("����D"));
        nodeArray.addNode(new Garage("����E"));

        System.out.println("========== after  =============");

        for (Car obj : objs) {
            System.out.println(nodeArray.get(obj));
        }
    }
}
