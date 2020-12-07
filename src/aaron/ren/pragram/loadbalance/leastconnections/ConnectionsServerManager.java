package aaron.ren.pragram.loadbalance.leastconnections;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author zhibo
 * @date 2019/5/16 16:25
 */
public class ConnectionsServerManager {
    public volatile static Map<String, ConnectionsServer> serverMap = new TreeMap<>();

    static {
        serverMap.put("192.168.1.1", new ConnectionsServer("192.168.1.1",1));
        serverMap.put("192.168.1.2", new ConnectionsServer("192.168.1.2",2));
        serverMap.put("192.168.1.3", new ConnectionsServer("192.168.1.3",3));
        serverMap.put("192.168.1.4", new ConnectionsServer("192.168.1.4",4));
    }
}
