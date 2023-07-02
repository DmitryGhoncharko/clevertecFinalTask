package ru.clevertec.ecl.clevertecfinaltask.unit.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import ru.clevertec.ecl.clevertecfinaltask.cache.Cache;
import ru.clevertec.ecl.clevertecfinaltask.cache.impl.RedisCache;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RedisCacheTest {
    private Cache<String, String> cache;
    private RedisTemplate<String, String> redisTemplate;
    private ValueOperations<String, String> valueOperations;

    @BeforeEach
    public void setUp() {
        redisTemplate = mock(RedisTemplate.class);
        valueOperations = mock(ValueOperations.class);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        cache = new RedisCache<>(redisTemplate);
    }

    @Test
    void testGet() {
        when(valueOperations.get("key")).thenReturn("value");
        Optional<String> value = cache.get("key");
        verify(valueOperations, times(1)).get("key");
        assertEquals("value", value.orElse(null));
    }


    @Test
    void testRemove() {
        cache.remove("key");
        verify(redisTemplate, times(1)).delete("key");
    }

    @Test
    void testPut() {
        cache.put("key", "value");
        verify(redisTemplate.opsForValue(), times(1)).set("key", "value");
    }
}

