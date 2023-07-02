package ru.clevertec.ecl.clevertecfinaltask.config;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import ru.clevertec.ecl.clevertecfinaltask.cache.Cache;

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

import java.util.Arrays;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
@ComponentScan("ru.clevertec.ecl.clevertecfinaltask.service")
@ComponentScan("ru.clevertec.ecl.clevertecfinaltask.cache")
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
    public Cache<String, Object> cache() {
        String activeProfile = Arrays.stream(environment.getActiveProfiles()).findFirst().orElse("");
        if (activeProfile.equalsIgnoreCase("LRU")) {
            return lruCache();
        } else if (activeProfile.equalsIgnoreCase("LFU")) {
            return lfuCache();
        } else if (activeProfile.equalsIgnoreCase("redis")) {
            return redisCache(redisTemplate(redisConnectionFactory()));
        }
        throw new IllegalArgumentException("Invalid cache profile: " + activeProfile);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("localhost"); // Установите хост Redis
        jedisConnectionFactory.setPort(6379); // Установите порт Redis
        return jedisConnectionFactory;
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
