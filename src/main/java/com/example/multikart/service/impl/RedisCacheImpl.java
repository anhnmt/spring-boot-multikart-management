package com.example.multikart.service.impl;

import com.example.multikart.service.RedisCache;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheImpl implements RedisCache {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void putObject(String key, Object hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);

    }

    @Override
    public <T> T getObject(String key, Object hashKey, TypeReference<T> classes) {
        return (T) redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
