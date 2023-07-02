package ru.clevertec.ecl.clevertecfinaltask.unit.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.ecl.clevertecfinaltask.cache.Cache;
import ru.clevertec.ecl.clevertecfinaltask.cache.impl.LRUCache;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {
    private Cache<Integer, String> cache;

    @BeforeEach
    public void setUp() {
        cache = new LRUCache<>(3);
    }

    @Test
    void testGetNonExistentKey() {
        Optional<String> value = cache.get(1);
        assertFalse(value.isPresent());
    }

    @Test
    void testPutAndGet() {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        Optional<String> value1 = cache.get(1);
        Optional<String> value2 = cache.get(2);
        Optional<String> value3 = cache.get(3);

        assertTrue(value1.isPresent());
        assertEquals("One", value1.get());

        assertTrue(value2.isPresent());
        assertEquals("Two", value2.get());

        assertTrue(value3.isPresent());
        assertEquals("Three", value3.get());
    }

    @Test
    void testEviction() {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        cache.put(4, "Four");
        Optional<String> value1 = cache.get(1);
        Optional<String> value2 = cache.get(2);
        Optional<String> value3 = cache.get(3);
        Optional<String> value4 = cache.get(4);
        assertFalse(value1.isPresent());
        assertTrue(value2.isPresent());
        assertEquals("Two", value2.get());
        assertTrue(value3.isPresent());
        assertEquals("Three", value3.get());
        assertTrue(value4.isPresent());
        assertEquals("Four", value4.get());
    }

    @Test
    void testRemove() {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.remove(1);
        Optional<String> value1 = cache.get(1);
        Optional<String> value2 = cache.get(2);
        assertFalse(value1.isPresent());
        assertTrue(value2.isPresent());
        assertEquals("Two", value2.get());
    }

    @Test
    void testClear() {
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.clear();
        Optional<String> value1 = cache.get(1);
        Optional<String> value2 = cache.get(2);
        assertFalse(value1.isPresent());
        assertFalse(value2.isPresent());
    }
}

