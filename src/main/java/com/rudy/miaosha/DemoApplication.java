package com.rudy.miaosha;

import com.rudy.miaosha.redis.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
public class DemoApplication {

    @Autowired
     private  RedisConfig redisConfig;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}
