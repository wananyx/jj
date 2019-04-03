package com.yx.feign.service;

import org.springframework.stereotype.Component;

@Component
public class HiServiceHystric implements HiService {
    @Override
    public String sayHi(String name) {
        return "，这是feign的熔断器，我们发现你的服务挂了";
    }
}
