package com.yx.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: JST
 * @Date: 2019/4/19 10:06
 */
@Configuration
public class PasswordEncoderConfig {
    @Bean
    public BCryptPasswordEncoder newPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
