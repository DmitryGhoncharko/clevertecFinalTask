package ru.clevertec.ecl.clevertecfinaltask.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.clevertecfinaltask.cache.impl.LFUCache;
import ru.clevertec.ecl.clevertecfinaltask.cache.impl.LRUCache;
import ru.clevertec.ecl.clevertecfinaltask.entity.News;

@Component
public class NewsCacheManager {
    private final Cache<Long, News> cache;

    public NewsCacheManager(@Value("${cache.algorithm}") String algorithm,
                            @Value("${cache.maxSize}") int maxSize) {
        if (algorithm.equalsIgnoreCase("LRU")) {
            cache = new LRUCache<>(maxSize);
        } else if (algorithm.equalsIgnoreCase("LFU")) {
            cache = new LFUCache<>(maxSize);
        } else {
            throw new IllegalArgumentException("Invalid cache algorithm");
        }
    }

    public Cache<Long, News> getCache() {
        return cache;
    }
}

