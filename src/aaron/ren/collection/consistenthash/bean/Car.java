package aaron.ren.collection.consistenthash.bean;

public class Car {
    String key;

    public  Car(String key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return "Car{" +
                "key='" + key + '\'' +
                '}';
    }
}
