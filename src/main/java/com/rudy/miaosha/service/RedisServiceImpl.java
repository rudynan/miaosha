package com.rudy.miaosha.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.rudy.miaosha.redis.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisServiceImpl implements RedisService {


    @Autowired
    private JedisPool jedisPool;

    @Override
    public <T> boolean set(String key, T value) throws Exception {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = obj2str(value);
            if (str == null || str.length() <=0){
                return false;
            }
            jedis.set(key,str);
            return true;
        }finally {
            returnPool(jedis);
        }
    }

    private <T> String obj2str(T value) {
        if (value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class || clazz == Long.class || clazz == long.class){
            return JSON.toJSONString(String.valueOf(value));
        }else if (clazz == String.class){
            return (String) value;
        }else {
            return JSON.toJSONString(value);
        }

    }

    @Override
    public <T> T get(String key,Class<T> clazz) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            return str2Obj(str,clazz);
        }finally {
            returnPool(jedis);
        }
    }

    private <T> T str2Obj(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null ){
            return null;
        }
        if (clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }else if (clazz == String.class){
            return (T) str;
        }else if (clazz == Long.class){
            return (T) Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private void returnPool(Jedis jedis){
        jedis.close();
    }


}
