package aaron.ren.pragram.loadbalance.leastconnections;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


public class LeastBalance {

    public static String getServer() {
        Map<String, ConnectionsServer> serverMap = new TreeMap<>(ConnectionsServerManager.serverMap);
        Iterator<String> iterator = serverMap.keySet().iterator();

        ConnectionsServer minConnectionsServer = null;
        while (iterator.hasNext()) {
            ConnectionsServer server = serverMap.get(iterator.next());
            if (minConnectionsServer == null) {
                minConnectionsServer = server;
            }

            if (minConnectionsServer.getConnnections() > server.getConnnections()) {
                minConnectionsServer = server;
            }
        }

        minConnectionsServer.setConnnections(minConnectionsServer.getConnnections() + 1);
        ConnectionsServerManager.serverMap.put(minConnectionsServer.getServer(), minConnectionsServer);
        System.out.println(String.format("ip=%s, connections=%s", minConnectionsServer.getServer(), minConnectionsServer.getConnnections()));
        return minConnectionsServer.getServer();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String server = getServer();
        }
    }
}

