package com.yx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class JRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(JRegistryApplication.class,args);
    }
}
