package com.example.servicea;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@Slf4j
@AllArgsConstructor
public class ServiceAApplication {

    private final RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ServiceAApplication.class, args);
    }


    @GetMapping("/test")
    public Object index() {
        log.info("index");
        Object forObject = restTemplate.getForObject("http://localhost:5051/test", Object.class);
        System.out.println("forObject = " + forObject);
        return forObject;
    }
}
