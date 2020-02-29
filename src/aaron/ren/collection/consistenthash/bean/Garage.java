package aaron.ren.collection.consistenthash.bean;

import java.util.HashMap;
import java.util.Map;

public class Garage {

    Map<Integer, Car> node = new HashMap<>();
    String name;

    public Garage(String name) {
        this.name = name;
    }

    public void putObj(Car obj) {
        node.put(obj.hashCode(), obj);
    }

    public  Car getObj(Car obj) {
        return node.get(obj.hashCode());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
