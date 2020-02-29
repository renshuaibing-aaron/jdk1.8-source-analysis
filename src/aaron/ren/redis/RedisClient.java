package aaron.ren.redis;

import redis.clients.jedis.Jedis;

import java.io.*;

public class RedisClient {
    public static void main(String[] args) throws Exception {
     // 创建jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 操作string数据类型
        jedis.set("username", "helloworld");
        // 根据key取出对应的value值
        String value = jedis.get("username");
        // 值输出
        System.out.println(value);

        Person person=new Person("rsb",123);
        Person person2=new Person("rsb",123);

        byte[] personByte = Byte_File_Object.getBytesFromObject(person);
        byte[] person2Byte= Byte_File_Object.getBytesFromObject(person2);
        //序列化
        jedis.set("rsb".getBytes(),"rwx".getBytes());


        jedis.set("person".getBytes(),personByte);
        jedis.set("person2".getBytes(),person2Byte);

        System.out.println(jedis.get("rsb".getBytes()));

        System.out.println(jedis.get("person".getBytes()));
        System.out.println(Byte_File_Object.getObjectFromBytes(jedis.get("person2".getBytes())));
        // 关闭连接
        jedis.close();
    }

    static class Byte_File_Object {
        public static Object getObjectFromBytes(byte[] objBytes) throws Exception {
            if (objBytes == null || objBytes.length == 0) {
                return null;
            }
            ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
            ObjectInputStream ois = new ObjectInputStream(bi);
            return ois.readObject();
        }

        public static byte[] getBytesFromObject(Serializable obj) throws Exception {
            if (obj == null) {
                return null;
            }
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bo);
            oos.writeObject(obj);
            return bo.toByteArray();
        }

    }
}
