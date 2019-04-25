package com.yx.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
//@EnableZuulProxy   原来gateway和zuul不是一个东西。。。😭
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

    @RequestMapping("/fallback")
    public String fallback(){
        return "这是gateway的熔断器，我们发现你的服务挂了";
    }
}
