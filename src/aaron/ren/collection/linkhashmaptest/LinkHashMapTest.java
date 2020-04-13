package aaron.ren.collection.linkhashmaptest;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkHashMapTest {
    public static void main(String[] args) {


      //  accessByPut();
        accessByFind();


    }

    private static void accessByPut() {
        LinkedHashMap map=new LinkedHashMap();
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        map.put("4","d");
        map.put("5","e");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<String,String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            System.out.println(value);
        }
    }

    private static void accessByFind() {
        LinkedHashMap map=new LinkedHashMap(16,0.75f,true);
        map.put("1","a");
        map.put("2","b");
        map.put("3","c");
        map.put("4","d");
        map.put("5","e");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<String,String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            System.out.println(value);
        }

        String str = (String) map.get("4");
        System.out.println("-----------------");
        Iterator<Map.Entry<String, String>> iterator2 = map.entrySet().iterator();
        while(iterator2.hasNext()){
            Map.Entry<String,String> next = iterator2.next();
            String key = next.getKey();
            String value = next.getValue();
            System.out.println(value);
        }
    }
}
