package aaron.ren.hashmap;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String,String> map =new ConcurrentHashMap();
        map.put("testKey1","testValue");
        map.put("testKey2","testValue");
        map.put("testKey3","testValue");
        map.put("testKey4","testValue");
        map.put("testKey5","testValue");
        map.put("testKey6","testValue");
        map.put("testKey7","testValue");
        map.put("testKey8","testValue");
        map.put("testKey9","testValue");
        map.put("testKey10","testValue");
        System.out.println(map.get("testKey"));

        System.out.println(map.size());
    }
}
