package aaron.ren.hashmap;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        Map<KeyClass,String> map=new HashMap<>();

        KeyClass keyClass=new KeyClass("≤‚ ‘");
        map.put(keyClass,"value");

        System.out.println(map.get(new KeyClass("≤‚ ‘")));

    }

    private static class KeyClass {
        public KeyClass(String name) {
            this.name=name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String name;
    }
}
