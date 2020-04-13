package aaron.ren.pragram.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private final int CACHE_SIZE;

    public LRUCache(int initialCapacity,      float loadFactor,      boolean accessOrder) {
        super(initialCapacity, loadFactor, true);
        CACHE_SIZE = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {


        return super.removeEldestEntry(entry);
    }


    public static void main(String[] args) {

        LRUCache<Character, Integer> lru = new LRUCache<Character, Integer>(
                16, 0.75f, true);

        String s = "0123456789";
        for (int i = 0; i < s.length(); i++) {
            lru.put(s.charAt(i), i);
        }
        System.out.println("LRU中key为h的Entry的值为： " + lru.get('5'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
    }


}
