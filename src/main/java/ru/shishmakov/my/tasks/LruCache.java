package ru.shishmakov.my.tasks;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LruCache implements Cache<String, Object> {
    private final int MAX_CAPACITY = 5;
    private final Map<String, Object> lruCache;

    public LruCache() {
        this.lruCache = new LinkedHashMap<>(MAX_CAPACITY, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
                return size() > MAX_CAPACITY;
            }
        };
    }

    @Override
    public void set(String key, Object value) {
        lruCache.put(key, value);
    }

    @Override
    public Object get(String key) {
        return lruCache.get(key);
    }

    public void iterate() {
        lruCache.forEach((key, value) ->
                System.out.println("key: " + key + " value: " + value)
        );
    }

    public static void main(String[] args) {
        LruCache cache = new LruCache();
        List<Integer> items = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        items.forEach(value ->
                cache.set(value.toString(), value)
        );

        Object object = cache.get("5"); // спасаем 5
        cache.set("10", 10); // новое значение
        cache.iterate();
    }
}

interface Cache<K, V> {
    void set(K key, V value);

    V get(K key);
}
