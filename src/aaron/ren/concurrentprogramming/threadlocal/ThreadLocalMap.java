package aaron.ren.concurrentprogramming.threadlocal;

import java.util.HashMap;
import java.util.Map;
//ΪʲôҪ��װһ����
public class ThreadLocalMap {
    /**
     * The constant threadContext.
     */
    private final static ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new MapThreadLocal();

    /**
     * Put.
     *
     * @param key   the key
     * @param value the value
     */
    public static void put(String key, Object value) {
        getContextMap().put(key, value);
    }

    /**
     * Remove object.
     *
     * @param key the key
     *
     * @return the object
     */
    public static Object remove(String key) {
        return getContextMap().remove(key);
    }

    /**
     * Get object.
     *
     * @param key the key
     *
     * @return the object
     */
    public static Object get(String key) {
        return getContextMap().get(key);
    }

    private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {
        /**
         * Initial value map.
         *
         * @return the map
         */
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>(8) {

                private static final long serialVersionUID = 3637958959138295593L;

                @Override
                public Object put(String key, Object value) {
                    return super.put(key, value);
                }
            };
        }
    }

    /**
     * ȡ��thread context Map��ʵ����
     *
     * @return thread context Map��ʵ��
     */
    private static Map<String, Object> getContextMap() {
        return THREAD_CONTEXT.get();
    }

    /**
     * �����߳����б�holdס�Ķ����Ա����ã�
     */
    public static void remove() {
        getContextMap().clear();
    }
}
