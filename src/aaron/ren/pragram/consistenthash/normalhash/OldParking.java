package aaron.ren.pragram.consistenthash.normalhash;

import aaron.ren.pragram.consistenthash.bean.Car;
import aaron.ren.pragram.consistenthash.bean.Garage;

public class OldParking {

    Garage[] nodes = new Garage[1024];
    int size = 0;

    public void addNode(Garage node) {
        nodes[size++] = node;
    }

    public Car get(Car obj) {
        int index = obj.hashCode() % size;
        return nodes[index].getObj(obj);
    }

    public   void put(Car obj) {
        int index = obj.hashCode() % size;
        nodes[index].putObj(obj);
    }
}
