package com.yx.auth.service;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: JST
 * @Date: 2019/4/23 15:00
 * 存储code到redis，并设置过期时间，10分钟<br>
 * value为OAuth2Authentication序列化后的字节<br>
 * 因为OAuth2Authentication没有无参构造函数<br>
 * redisTemplate.opsForValue().set(key, value, timeout, unit);
 * 这种方式直接存储的话，redisTemplate.opsForValue().get(key)的时候有些问题，
 * 所以这里采用最底层的方式存储，get的时候也用最底层的方式获取
 */

@Service
public class RedisAuthorizationCodeServices extends RandomValueAuthorizationCodeServices {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    protected void store(String code, OAuth2Authentication oAuth2Authentication) {
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set(codeKey(code).getBytes(),SerializationUtils.serialize(oAuth2Authentication),
                        Expiration.from(10,TimeUnit.MINUTES),RedisStringCommands.SetOption.UPSERT);
                return 1L;
            }
        });
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        OAuth2Authentication oAuth2Authentication = redisTemplate.execute(new RedisCallback<OAuth2Authentication>() {

            @Override
            public OAuth2Authentication doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyByte = codeKey(code).getBytes();
                byte[] valueByte = connection.get(keyByte);

                if (valueByte != null) {
                    connection.del(keyByte);
                    return org.springframework.security.oauth2.common.util.SerializationUtils.deserialize(valueByte);
                }

                return null;
            }
        });

        return oAuth2Authentication;
    }

    /**
     * 拼装redis中key的前缀
     *
     * @param code
     */
    private String codeKey(String code) {
        return "oauth2:codes:" + code;
    }
}
