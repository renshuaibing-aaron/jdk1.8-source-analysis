package aaron.ren.pragram.lru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  ����hashmapʵ��һ�������ڹ��ܵ�LRU���湤����
 */
public class ConcurrentHashMapCacheUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(ConcurrentHashMapCacheUtils.class);

    /**
     * ����������
     */
    private static final Integer CACHE_MAX_NUMBER = 1000;
    /**
     * ��ǰ�������
     */
    private static Integer CURRENT_SIZE = 0;
    /**
     * ʱ��һ����
     */
    static final Long ONE_MINUTE = 60 * 1000L;
    /**
     * �������
     */
    private static final Map<String, CacheObj> CACHE_OBJECT_MAP = new ConcurrentHashMap<>();
    /**
     * �����¼�˻���ʹ�õ����һ�εļ�¼�����ʹ�õ�����ǰ��
     */
    private static final List<String> CACHE_USE_LOG_LIST = new LinkedList<>();
    /**
     * ������ڻ����Ƿ�������
     */
    private static volatile Boolean CLEAN_THREAD_IS_RUN = false;


    /**
     * ���û���
     */
    public static void setCache(String cacheKey, Object cacheValue, long cacheTime) {
        Long ttlTime = null;
        if (cacheTime <= 0L) {
            if (cacheTime == -1L) {
                ttlTime = -1L;
            } else {
                return;
            }
        }
        checkSize();
        saveCacheUseLog(cacheKey);
        CURRENT_SIZE = CURRENT_SIZE + 1;
        if (ttlTime == null) {
            ttlTime = System.currentTimeMillis() + cacheTime;
        }
        CacheObj cacheObj = new CacheObj(cacheValue, ttlTime);
        CACHE_OBJECT_MAP.put(cacheKey, cacheObj);
        LOGGER.info("have set key :" + cacheKey);
    }
    /**
     * ���û���
     */
    public static void setCache(String cacheKey, Object cacheValue) {
        setCache(cacheKey, cacheValue, -1L);
    }
    /**
     * ��ȡ����
     */
    public static Object getCache(String cacheKey) {
        startCleanThread();
        if (checkCache(cacheKey)) {
            saveCacheUseLog(cacheKey);
            return CACHE_OBJECT_MAP.get(cacheKey).getCacheValue();
        }
        return null;
    }
    public static boolean isExist(String cacheKey) {
        return checkCache(cacheKey);
    }
    /**
     * ɾ�����л���
     */
    public static void clear() {
        LOGGER.info("have clean all key !");
        CACHE_OBJECT_MAP.clear();
        CURRENT_SIZE = 0;
    }
    /**
     * ɾ��ĳ������
     */
    public static void deleteCache(String cacheKey) {
        Object cacheValue = CACHE_OBJECT_MAP.remove(cacheKey);
        if (cacheValue != null) {
            LOGGER.info("have delete key :" + cacheKey);
            CURRENT_SIZE = CURRENT_SIZE - 1;
        }
    }
    /**
     * �жϻ����ڲ���,��û����
     */
    private static boolean checkCache(String cacheKey) {
        CacheObj cacheObj = CACHE_OBJECT_MAP.get(cacheKey);
        if (cacheObj == null) {
            return false;
        }
        if (cacheObj.getTtlTime() == -1L) {
            return true;
        }
        if (cacheObj.getTtlTime() < System.currentTimeMillis()) {
            deleteCache(cacheKey);
            return false;
        }
        return true;
    }
    /**
     * ɾ��������δʹ�õĻ���
     */
    private static void deleteLRU() {
        LOGGER.info("delete Least recently used run!");
        String cacheKey = null;
        synchronized (CACHE_USE_LOG_LIST) {
            if (CACHE_USE_LOG_LIST.size() >= CACHE_MAX_NUMBER - 10) {
                cacheKey = CACHE_USE_LOG_LIST.remove(CACHE_USE_LOG_LIST.size() - 1);
            }
        }
        if (cacheKey != null) {
            deleteCache(cacheKey);
        }
    }

    /**
     * ɾ�����ڵĻ���
     */
    static void deleteTimeOut() {
        LOGGER.info("delete time out run!");
        List<String> deleteKeyList = new LinkedList<>();
        for(Map.Entry<String, CacheObj> entry : CACHE_OBJECT_MAP.entrySet()) {
            if (entry.getValue().getTtlTime() < System.currentTimeMillis() && entry.getValue().getTtlTime() != -1L) {
                deleteKeyList.add(entry.getKey());
            }
        }
        for (String deleteKey : deleteKeyList) {
            deleteCache(deleteKey);
        }
        LOGGER.info("delete cache count is :" + deleteKeyList.size());
    }
    /**
     * ����С
     * ����ǰ��С����Ѿ��ﵽ����С
     * ����ɾ�����ڻ��棬������ڻ���ɾ�������Ǵﵽ��󻺴���Ŀ
     * ɾ�����δʹ�û���
     */
    private static void checkSize() {
        if (CURRENT_SIZE >= CACHE_MAX_NUMBER) {
            deleteTimeOut();
        }
        if (CURRENT_SIZE >= CACHE_MAX_NUMBER) {
            deleteLRU();
        }
    }

    /**
     * ���滺���ʹ�ü�¼
     */
    private static synchronized void saveCacheUseLog(String cacheKey) {
        synchronized (CACHE_USE_LOG_LIST) {
            CACHE_USE_LOG_LIST.remove(cacheKey);
            CACHE_USE_LOG_LIST.add(0,cacheKey);
        }
    }

    /**
     * ���������̵߳�����״̬Ϊ��������
     */
    static void setCleanThreadRun() {
        CLEAN_THREAD_IS_RUN = true;
    }

    /**
     * ����������ڻ�����߳�
     */
    private static void startCleanThread() {
        if (!CLEAN_THREAD_IS_RUN) {
            CleanTimeOutThread cleanTimeOutThread = new CleanTimeOutThread();
            Thread thread = new Thread(cleanTimeOutThread);
            //����Ϊ��̨�ػ��߳�
            thread.setDaemon(true);
            thread.start();
        }
    }

    /**
     * ÿһ��������һ�ι��ڻ���
     */
    private  static   class CleanTimeOutThread implements Runnable{

        @Override
        public void run() {
            ConcurrentHashMapCacheUtils.setCleanThreadRun();
            while (true) {
                System.out.println("clean thread run ");
                ConcurrentHashMapCacheUtils.deleteTimeOut();
                try {
                    Thread.sleep(ConcurrentHashMapCacheUtils.ONE_MINUTE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
  private  static   class CacheObj {
        /**
         * �������
         */
        private Object CacheValue;
        /**
         * �������ʱ��
         */
        private Long ttlTime;

        CacheObj(Object cacheValue, Long ttlTime) {
            CacheValue = cacheValue;
            this.ttlTime = ttlTime;
        }

        Object getCacheValue() {
            return CacheValue;
        }

        Long getTtlTime() {
            return ttlTime;
        }

        @Override
        public String toString() {
            return "CacheObj {" +
                    "CacheValue = " + CacheValue +
                    ", ttlTime = " + ttlTime +
                    '}';
        }
    }



}


