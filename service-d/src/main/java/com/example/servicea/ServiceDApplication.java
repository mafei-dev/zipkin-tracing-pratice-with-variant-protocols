package com.example.servicea;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
@Slf4j
@AllArgsConstructor
public class ServiceDApplication {
    private final Tracer tracer;
    private final RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ServiceDApplication.class, args);
    }


    @GetMapping("/test")
    public Object index(@RequestHeader("X-B3-TraceId") String traceId,
                        @RequestHeader("X-B3-ParentSpanId") String parentId) {
        log.info("traceId {},", traceId);
        log.info("parentId {},", parentId);
        String _parentId = MDC.get("parentId");
        log.info("_parentId : {}", _parentId);
        MDC.put("traceId", UUID.randomUUID().toString());
        log.info("index");
        Object forObject = restTemplate.getForObject("http://localhost:5054/test", Object.class);
        return forObject;
    }
}
