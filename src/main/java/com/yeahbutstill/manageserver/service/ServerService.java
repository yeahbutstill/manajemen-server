package com.yeahbutstill.manageserver.service;

import com.yeahbutstill.manageserver.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {

    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> listServers(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);

}
