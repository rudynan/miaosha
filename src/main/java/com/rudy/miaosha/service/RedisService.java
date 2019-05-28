package com.rudy.miaosha.service;

import com.rudy.miaosha.redis.KeyPrefix;

public interface RedisService {
    <T> T get(KeyPrefix keyPrefix,String key,Class<T> clazz)throws Exception;
    <T> boolean set(KeyPrefix keyPrefix, String key, T value)throws Exception;
    Long incr(KeyPrefix keyPrefix,String key) throws Exception;
    Long decr(KeyPrefix keyPrefix,String key) throws Exception;
    Boolean exist(KeyPrefix keyPrefix, String key) throws Exception;
}
