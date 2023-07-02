package ru.clevertec.ecl.clevertecfinaltask.cache.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.clevertecfinaltask.cache.Cache;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RedisCache<K, V> implements Cache<K, V> {

    private final RedisTemplate<K, V> redisTemplate;

    @Override
    public Optional<V> get(K key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    @Override
    public void put(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void remove(K key) {
        redisTemplate.delete(key);
    }

    @Override
    public void clear() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }
}

