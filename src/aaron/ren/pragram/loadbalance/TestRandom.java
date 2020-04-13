package aaron.ren.pragram.loadbalance;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Title:
 * Description:随机
 * @author renshuaibing
 */
public class TestRandom {
    //    1.定义map, key-ip,value-weight
    static Map<String,Integer> ipMap=new HashMap<>();
    static {
        ipMap.put("192.168.13.1",1);
        ipMap.put("192.168.13.2",2);
        ipMap.put("192.168.13.3",4);


    }
    public String Random() {
        Map<String,Integer> ipServerMap=new ConcurrentHashMap<>();
        ipServerMap.putAll(ipMap);

        Set<String> ipSet=ipServerMap.keySet();

        //定义一个list放所有server
        ArrayList<String> ipArrayList=new ArrayList<String>();
        ipArrayList.addAll(ipSet);

        //循环随机数
        Random random=new Random();
        //随机数在list数量中取（1-list.size）
        int pos=random.nextInt(ipArrayList.size());
        String serverNameReturn= ipArrayList.get(pos);
        return  serverNameReturn;
    }

    public static void main(String[] args) {
        TestRandom testRandom=new TestRandom();
        for (int i =0;i<10;i++){
            String server=testRandom.Random();
            System.out.println(server);
        }


    }
}
