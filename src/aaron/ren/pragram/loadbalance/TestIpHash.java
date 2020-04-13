package aaron.ren.pragram.loadbalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Title:
 * Description:
 */
public class TestIpHash {
    //    1.����map, key-ip,value-weight
    static Map<String,Integer> ipMap=new HashMap<>();
    static {
        ipMap.put("192.168.13.1",1);
        ipMap.put("192.168.13.2",2);
        ipMap.put("192.168.13.3",4);
    }
    public String ipHash(String clientIP){
        Map<String,Integer> ipServerMap=new ConcurrentHashMap<>();
        ipServerMap.putAll(ipMap);

        //    2.ȡ����key,�ŵ�set��
        Set<String> ipset=ipServerMap.keySet();

        //    3.set�ŵ�list��Ҫѭ��listȡ��
        ArrayList<String> iplist=new ArrayList<String>();
        iplist.addAll(ipset);

        //��ip��hashcodeֵȡ������ÿ�ζ�һ����
        int hashCode=clientIP.hashCode();
        int serverListsize=iplist.size();
        int pos=hashCode%serverListsize;
        return iplist.get(pos);

    }

    public static void main(String[] args) {
        TestIpHash iphash=new TestIpHash();
        String servername= iphash.ipHash("192.168.21.2");
        System.out.println(servername);
    }

}
