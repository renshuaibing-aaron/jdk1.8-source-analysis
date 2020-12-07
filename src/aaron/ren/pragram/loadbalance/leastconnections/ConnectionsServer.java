package aaron.ren.pragram.loadbalance.leastconnections;

import java.io.Serializable;

public class ConnectionsServer implements Serializable {
    private static final long serialVersionUID = 7246747589293111189L;

    private String server;
    private Integer connnections;

    public ConnectionsServer(String server, Integer connnections){
        this.server = server;
        this.connnections = connnections;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getConnnections() {
        return connnections;
    }

    public void setConnnections(Integer connnections) {
        this.connnections = connnections;
    }
}

