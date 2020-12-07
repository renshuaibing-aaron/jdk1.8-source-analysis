package aaron.ren.pragram.Leetcode.lru;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * ����linkHashMap��ʵ��LRU�㷨
 */
public class LRUCache {
    // ����װitem��LinkedHashMap�������
    CacheMap cache;

    public LRUCache(int capacity) {
        cache = new CacheMap(capacity);
    }
    /**
     * ÿ��get�����е�ֵ��������ӦԪ��ɾ�����²��룬��ʱ���ͻ�λ�����µ�λ��
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
     * LinkedHashMapֻ��ʵ������Ƴ����������
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

