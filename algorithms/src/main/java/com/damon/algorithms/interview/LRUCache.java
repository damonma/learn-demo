package com.damon.algorithms.interview;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author damon
 * @version 2020/1/16
 */
public class LRUCache {
    /**
     * 题目：请实现一个LRU过期策略的本地缓存。
     */

    /**
     * 思路：利用LinkedHashMap本身的特性，每次查询或更新后，都删除原有对象，然后重新添加到最后
     */

    int capacity;
    LinkedHashMap<String, Object> cache;

    public LRUCache(int capacity) {
        cache = new LinkedHashMap<>(capacity);
        this.capacity = capacity;
    }

    public synchronized Object get(String key) {
        if (!cache.containsKey(key)) {
            return null;
        }

        Object value = cache.get(key);
        cache.remove(key);
        cache.put(key, value);

        return value;
    }

    public synchronized void put(String key, Object value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }

        if (capacity == cache.size()) {
            Set<String> keySet = cache.keySet();
            Iterator<String> iterator = keySet.iterator();
            cache.remove(iterator.next());
        }

        cache.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.put("a", "a");
        cache.put("b", "b");
        cache.put("c", "c");
        cache.put("d", "d");
        System.out.println(cache.get("a"));
        System.out.println(cache.get("b"));
        System.out.println(cache.get("d"));
    }
}
