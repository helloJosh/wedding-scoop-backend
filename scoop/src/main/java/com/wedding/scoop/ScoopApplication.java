package com.wedding.scoop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ScoopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoopApplication.class, args);
    }

}
