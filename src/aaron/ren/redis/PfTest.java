package aaron.ren.redis;

import redis.clients.jedis.Jedis;

public class PfTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("47.111.9.173", 6379);
        System.out.println("==============");
        for (int i = 0; i < 1000; i++) {
            jedis.pfadd("codehole", "user" + i);
            long total = jedis.pfcount("codehole");
            if (total != i + 1) {
                System.out.printf("%d %d\n", total, i + 1);
                break;
            }
        }
        jedis.close();
    }

}