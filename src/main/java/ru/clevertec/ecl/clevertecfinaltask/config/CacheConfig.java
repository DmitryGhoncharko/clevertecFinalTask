package ru.clevertec.ecl.clevertecfinaltask.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ru.clevertec.ecl.clevertecfinaltask.cache.Cache;
import ru.clevertec.ecl.clevertecfinaltask.cache.impl.LFUCache;
import ru.clevertec.ecl.clevertecfinaltask.cache.impl.LRUCache;
import ru.clevertec.ecl.clevertecfinaltask.cache.impl.RedisCache;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
@ComponentScan("ru.clevertec.ecl.clevertecfinaltask.service.impl")
public class CacheConfig {

    private final Environment environment;

    @Bean
    @Profile("LRU")
    public Cache<String, Object> lruCache() {
        int maxSize = Integer.parseInt(Objects.requireNonNull(environment.getProperty("cache.lru-max-size")));
        return new LRUCache<>(maxSize);
    }

    @Bean
    @Profile("LFU")
    public Cache<String, Object> lfuCache() {
        int maxSize = Integer.parseInt(Objects.requireNonNull(environment.getProperty("cache.lfu-max-size")));
        return new LFUCache<>(maxSize);
    }

    @Bean
    @Profile("redis")
    public Cache<String, Object> redisCache(RedisTemplate<String, Object> redisTemplate) {
        return new RedisCache<>(redisTemplate);
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
