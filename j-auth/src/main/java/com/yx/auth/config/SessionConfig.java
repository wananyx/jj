package com.yx.auth.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 开启session共享
 */
@EnableRedisHttpSession
public class SessionConfig {

}
