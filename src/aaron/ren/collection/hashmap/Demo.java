package aaron.ren.collection.hashmap;

import java.util.HashMap;

public class Demo {
    public static void main(String[] args) {

        HashMap<String,String> hashMap=new HashMap<>(2);
        hashMap.put("one","firstvalue");
        System.out.println(hashMap.get("one"));
    }
}
