package aaron.ren.redis;

import redis.clients.jedis.Jedis;

import java.io.*;

public class RedisClient {
    public static void main(String[] args) throws Exception {
     // ����jedis����
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // ����string��������
        jedis.set("username", "helloworld");
        // ����keyȡ����Ӧ��valueֵ
        String value = jedis.get("username");
        // ֵ���
        System.out.println(value);

        Person person=new Person("rsb",123);
        Person person2=new Person("rsb",123);

        byte[] personByte = Byte_File_Object.getBytesFromObject(person);
        byte[] person2Byte= Byte_File_Object.getBytesFromObject(person2);
        //���л�
        jedis.set("rsb".getBytes(),"rwx".getBytes());



        jedis.set("person".getBytes(),personByte);
        jedis.set("person2".getBytes(),person2Byte);

        System.out.println(jedis.get("rsb".getBytes()));

        System.out.println(jedis.get("person".getBytes()));
        System.out.println(Byte_File_Object.getObjectFromBytes(jedis.get("person2".getBytes())));
        // �ر�����
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
