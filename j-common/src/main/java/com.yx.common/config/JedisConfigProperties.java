package com.yx.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis-cluster配置属性注入
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class JedisConfigProperties {

    private String nodes;

    private int maxIdle;

    private int minIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private int maxWaitMills;

    private int connectionTimeOut;

    private int soTimeout;

    private int maxAttempts;


}
