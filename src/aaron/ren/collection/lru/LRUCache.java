package aaron.ren.collection.lru;

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
        System.out.println("LRU��keyΪh��Entry��ֵΪ�� " + lru.get('5'));
        System.out.println("LRU�Ĵ�С ��" + lru.size());
        System.out.println("LRU ��" + lru);
    }


}
