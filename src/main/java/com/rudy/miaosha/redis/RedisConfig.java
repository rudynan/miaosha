package com.rudy.miaosha.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.poolMaxTotal}")
    private int poolMaxTotal;
    @Value("${redis.poolMaxIdle}")
    private int poolMaxIdle;
    @Value("${redis.poolMaxWait}")
    private int poolMaxWait;

    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(poolMaxIdle);
        jedisPoolConfig.setMaxTotal(poolMaxTotal);
        jedisPoolConfig.setMaxWaitMillis(poolMaxWait*1000);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port, timeout * 1000);
        System.out.println(host+";"+port);
        return jedisPool;
    }
}
