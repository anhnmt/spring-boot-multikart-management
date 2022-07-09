package com.example.multikart.service;

import com.fasterxml.jackson.core.type.TypeReference;

public interface RedisCache {
    void putObject(String key, Object hashKey, Object value);

    <T> T getObject(String key, Object hashKey, TypeReference<T> classes);

    void delete(String key);
}
