package com.rudy.miaosha.service;

public interface RedisService {
    <T> T get(String key,Class<T> clazz)throws Exception;
    <T> boolean set(String key,T value)throws Exception;
}
