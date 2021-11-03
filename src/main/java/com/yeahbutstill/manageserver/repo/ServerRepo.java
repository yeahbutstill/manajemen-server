package com.yeahbutstill.manageserver.repo;

import com.yeahbutstill.manageserver.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}
