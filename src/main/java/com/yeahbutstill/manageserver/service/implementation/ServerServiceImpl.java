package com.yeahbutstill.manageserver.service.implementation;

import com.yeahbutstill.manageserver.enumeration.Status;
import com.yeahbutstill.manageserver.model.Server;
import com.yeahbutstill.manageserver.repo.ServerRepo;
import com.yeahbutstill.manageserver.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.Random;

import static com.yeahbutstill.manageserver.enumeration.Status.SERVER_DOWN;
import static com.yeahbutstill.manageserver.enumeration.Status.SERVER_UP;
import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    private String setServerImageUrl() {
        String[] imageName = { "server-1.png", "server-2.png", "server-3.png", "server-4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageName[new Random().nextInt(4)]).toUriString();
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        server.setStatus(inetAddress.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    private boolean isReachables(String ipAddress, int port, int timeOut) {
        try {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(ipAddress, port), timeOut);
            }
            return true;
        } catch (IOException exception) {
            return false;
        }
    }

    @Override
    public Collection<Server> listServers(int limit) {
        log.info("Fetching all server");
        return serverRepo.findAll(of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepo.findById(id).orElse(null);
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by ID: {}", id);
        serverRepo.deleteById(id);
        return TRUE;
    }

}
