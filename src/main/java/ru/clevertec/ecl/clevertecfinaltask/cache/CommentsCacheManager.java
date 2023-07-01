package ru.clevertec.ecl.clevertecfinaltask.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.clevertec.ecl.clevertecfinaltask.cache.impl.LFUCache;
import ru.clevertec.ecl.clevertecfinaltask.cache.impl.LRUCache;
import ru.clevertec.ecl.clevertecfinaltask.entity.Comment;

@Component
public class CommentsCacheManager {
    private final Cache<Long, Comment> cache;

    public CommentsCacheManager(@Value("${cache.algorithm}") String algorithm, @Value("${cache.maxSize}") int maxSize) {
        if (algorithm.equalsIgnoreCase("LRU")) {
            cache = new LRUCache<>(maxSize);
        } else if (algorithm.equalsIgnoreCase("LFU")) {
            cache = new LFUCache<>(maxSize);
        } else {
            throw new IllegalArgumentException("Invalid cache algorithm");
        }
    }

    public Cache<Long, Comment> getCache() {
        return cache;
    }
}
