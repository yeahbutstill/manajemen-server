package com.yeahbutstill.manageserver.resource;

import com.yeahbutstill.manageserver.model.Response;
import com.yeahbutstill.manageserver.service.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {

    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> responseResponseEntity() {
        return ResponseEntity.ok(
                Response.builder()
                        .localDateTime(now())
                        .data(new HashMap<String, String>() {{
                            put("servers", String.valueOf(serverService.listServers(30)));
                        }})
                        .message("Servers retrieved")
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

}
