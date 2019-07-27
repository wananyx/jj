package com.yx.user.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.yx.common.config.JedisConfigProperties;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(value = JedisConfigProperties.class)
public class JedisClusterConfig {

    @Resource
    private JedisConfigProperties jedisConfigProperties;

    @Bean
    public JedisCluster jedisClusterFactory() {
        String[] serverArray = jedisConfigProperties.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(nodes, jedisConfigProperties.getConnectionTimeOut(),jedisConfigProperties.getSoTimeout(),jedisConfigProperties.getMaxAttempts(),new GenericObjectPoolConfig());
    }

    /**
     * redisCluster配置
     *
     * @return
     */
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        Map<String, Object> source = new HashMap<String, Object>();
        source.put("spring.redis.cluster.nodes", jedisConfigProperties.getNodes());
        source.put("spring.redis.cluster.timeout", jedisConfigProperties.getConnectionTimeOut());
        RedisClusterConfiguration redisClusterConfiguration= new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
        return redisClusterConfiguration;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(redisClusterConfiguration(), jedisPoolConfig());
    }

    /**
     * jedis 连接池
     * @return
     */
    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(jedisConfigProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(jedisConfigProperties.getMaxWaitMills());
        jedisPoolConfig.setMinIdle(jedisConfigProperties.getMinIdle());
        jedisPoolConfig.setTestOnBorrow(jedisConfigProperties.isTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(jedisConfigProperties.isTestOnReturn());

        return jedisPoolConfig;
    }


    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        return template;
    }
}
