package com.yx.auth.service;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

import java.util.UUID;

/**
 * 解决同一username每次登陆access_token都相同的问题,看下RedisTokenStore的方法getAccessToken便知
 * @see org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator
 * @see org.springframework.security.oauth2.provider.token.TokenStore
 */
public class RandomAuthenticationKeyGenerator implements AuthenticationKeyGenerator {
    @Override
    public String extractKey(OAuth2Authentication oAuth2Authentication) {
        return UUID.randomUUID().toString();
    }
}
