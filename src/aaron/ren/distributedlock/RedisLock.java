package aaron.ren.distributedlock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

public class RedisLock {


    private String lock_key = "redis_lock"; //����

    protected long internalLockLeaseTime = 30000;//������ʱ��

    private long timeout = 999999; //��ȡ���ĳ�ʱʱ��

    //SET����Ĳ���
  // SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);

    JedisPool jedisPool;


    /**
     * ����
     * @param id
     * @return
     */
    public boolean lock(String id){
        Jedis jedis = jedisPool.getResource();
        Long start = System.currentTimeMillis();
        try{
            for(;;){
                //SET�����OK ����֤����ȡ���ɹ�
                String lock = jedis.set(lock_key, id);
                if("OK".equals(lock)){
                    return true;
                }
                //����ѭ���ȴ�����timeoutʱ������δ��ȡ���������ȡʧ��
                long l = System.currentTimeMillis() - start;
                if (l>=timeout) {
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            jedis.close();
        }
    }


    /**
     * ����
     * @param id
     * @return
     */
    public boolean unlock(String id){
        Jedis jedis = jedisPool.getResource();
        String script =
                "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                        "   return redis.call('del',KEYS[1]) " +
                        "else" +
                        "   return 0 " +
                        "end";
        try {
            Object result = jedis.eval(script, Collections.singletonList(lock_key),
                    Collections.singletonList(id));
            if("1".equals(result.toString())){
                return true;
            }
            return false;
        }finally {
            jedis.close();
        }
    }
}