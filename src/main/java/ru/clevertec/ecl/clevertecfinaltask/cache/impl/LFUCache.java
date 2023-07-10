package ru.clevertec.ecl.clevertecfinaltask.cache.impl;

import ru.clevertec.ecl.clevertecfinaltask.cache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LFUCache<K, V> implements Cache<K, V> {
    private final int maxSize;
    private final Map<K, V> cache;
    private final Map<K, Integer> frequencyMap;
    private final PriorityQueue<K> frequencyQueue;
    private final Lock lock;

    public LFUCache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
        this.frequencyQueue = new PriorityQueue<>((k1, k2) -> frequencyMap.get(k1) - frequencyMap.get(k2));
        this.lock = new ReentrantLock();
    }

    @Override
    public Optional<V> get(K key) {
        lock.lock();
        try {
            if (cache.containsKey(key)) {
                incrementFrequency(key);
                return Optional.ofNullable(cache.get(key));
            } else {
                return Optional.empty();
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void put(K key, V value) {
        lock.lock();
        try {
            if (cache.containsKey(key)) {
                cache.put(key, value);
                incrementFrequency(key);
            } else {
                if (cache.size() >= maxSize) {
                    evictLeastFrequent();
                }
                cache.put(key, value);
                frequencyMap.put(key, 1);
                frequencyQueue.offer(key);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(K key) {
        lock.lock();
        try {
            cache.remove(key);
            frequencyMap.remove(key);
            frequencyQueue.remove(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            cache.clear();
            frequencyMap.clear();
            frequencyQueue.clear();
        } finally {
            lock.unlock();
        }
    }

    private void incrementFrequency(K key) {
        int frequency = frequencyMap.get(key);
        frequencyMap.put(key, frequency + 1);
        frequencyQueue.remove(key);
        frequencyQueue.offer(key);
    }

    private void evictLeastFrequent() {
        K leastFrequentKey = frequencyQueue.poll();
        if (leastFrequentKey != null) {
            cache.remove(leastFrequentKey);
            frequencyMap.remove(leastFrequentKey);
        }
    }
}