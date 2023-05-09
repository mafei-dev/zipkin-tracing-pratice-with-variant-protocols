package com.example.servicea;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@Slf4j
@AllArgsConstructor
public class ServiceBApplication {
    private final RestTemplate restTemplate;
    public static void main(String[] args) {
        SpringApplication.run(ServiceBApplication.class, args);
    }


    @GetMapping("/test")
    public Object index() {
        log.info("index");
        Object forObject = restTemplate.getForObject("http://localhost:5052/test", Object.class);
        System.out.println("forObject = " + forObject);
        return forObject;
    }
}
