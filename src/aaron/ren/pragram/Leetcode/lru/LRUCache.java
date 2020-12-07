package aaron.ren.pragram.Leetcode.lru;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 利用linkHashMap来实现LRU算法
 */
public class LRUCache {
    // 用来装item的LinkedHashMap子类对象
    CacheMap cache;

    public LRUCache(int capacity) {
        cache = new CacheMap(capacity);
    }
    /**
     * 每次get容器中的值，都将相应元素删除重新插入，这时它就会位于最新的位置
     * @param key
     * @return
     */
    public int get(int key) {
        if (cache.containsKey(key)) {
            int value = cache.get(key);
            cache.remove(key);
            set(key, value);
            return value;
        }
        return -1;
    }

    public void set(int key, int value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
        cache.put(key, value);
    }


    /**
     * LinkedHashMap只能实现最旧移除而不会更新
     *
     * @author Chyace
     *
     */
    private class CacheMap extends LinkedHashMap<Integer, Integer> {

        private static final long serialVersionUID = 3240765461462956414L;

        private int MAX;

        CacheMap(int max) {
            this.MAX = max;
        }

        @Override
        protected boolean removeEldestEntry(Entry<Integer, Integer> eldest) {
            return size() > MAX;
        }

    }
}

