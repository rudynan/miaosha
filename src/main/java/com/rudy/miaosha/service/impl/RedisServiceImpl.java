package com.rudy.miaosha.service.impl;

import com.alibaba.fastjson.JSON;
import com.rudy.miaosha.redis.KeyPrefix;
import com.rudy.miaosha.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


@Service
public class RedisServiceImpl implements RedisService {


    @Autowired
    private JedisPool jedisPool;

    @Override
    public <T> boolean set(KeyPrefix keyPrefix, String key, T value) throws Exception {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = obj2str(value);
            if (str == null || str.length() <=0){
                return false;
            }
            String finalKey = keyPrefix.getPrefix()+key;
            int expireSeconds = keyPrefix.expireSeconds();
            if ( expireSeconds<= 0){

                jedis.set(finalKey,str);
            }else {
                jedis.setex(finalKey,expireSeconds,str);

            }
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
    public <T> T get(KeyPrefix keyPrefix,String key,Class<T> clazz) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(keyPrefix.getPrefix()+key);
            return str2Obj(str,clazz);
        }finally {
            returnPool(jedis);
        }
    }

    @Override
    public Long incr(KeyPrefix keyPrefix, String key) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long resp = jedis.incr(keyPrefix.getPrefix()+key);
            return resp;
        }finally {
            returnPool(jedis);
        }
    }
    /*
        如果减的这个值不是数值类型,那么数据设为0然后减1
     */
    @Override
    public Long decr(KeyPrefix keyPrefix, String key) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long resp = jedis.decr(keyPrefix.getPrefix()+key);
            return resp;
        }finally {
            returnPool(jedis);
        }
    }
    @Override
    public Boolean exist(KeyPrefix keyPrefix, String key) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Boolean exists = jedis.exists(keyPrefix.getPrefix() + key);
            return exists;
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
