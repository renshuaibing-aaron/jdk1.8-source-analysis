package aaron.ren.pragram.loadbalance;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Title:
 * Description:加权随机
 */
public class TestRobinRandom {

    //    1.定义map, key-ip,value-weight
    static Map<String,Integer> ipMap=new HashMap<>();
    static {
        ipMap.put("192.168.13.1",1);
        ipMap.put("192.168.13.2",2);
        ipMap.put("192.168.13.3",4);


    }
    public String RobinRandom(){
        Map<String,Integer> ipServerMap=new ConcurrentHashMap<>();
        ipServerMap.putAll(ipMap);

        Set<String> ipSet=ipServerMap.keySet();
        Iterator<String> ipIterator=ipSet.iterator();

        //定义一个list放所有server
        ArrayList<String> ipArrayList=new ArrayList<String>();

        //循环set，根据set中的可以去得知map中的value，给list中添加对应数字的server数量
        while (ipIterator.hasNext()){
            String serverName=ipIterator.next();
            Integer weight=ipServerMap.get(serverName);
            for (int i=0;i<weight;i++){
                ipArrayList.add(serverName);
            }
        }

        //循环随机数
        Random random=new Random();
        //随机数在list数量中取（1-list.size）
        int pos=random.nextInt(ipArrayList.size());
        String serverNameReturn= ipArrayList.get(pos);
        return  serverNameReturn;
    }

    public static void main(String[] args) {
        TestRobinRandom testRobinRandom=new TestRobinRandom();
        for (int i =0;i<10;i++){
            String server=testRobinRandom.RobinRandom();
            System.out.println(server);
        }


    }
}

