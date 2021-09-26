package com.jason.lee.learn;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LRUCache {
    private static LRU<Integer, Integer> cache;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        cache = new LRUCache().new LRU(n);
        while (sc.hasNext()) {
            String s = sc.next();
            if (s.equals("p")) {
                int key = sc.nextInt();
                int val = sc.nextInt();
                cache.put(key, val);
            } else {
                int key = sc.nextInt();
                int value = cache.get(key) == null ? -1 : cache.get(key);
                System.out.println(value);
            }
        }

    }

    class LRU<K, V> extends LinkedHashMap<K, V> {
        private int SIZE;

        public LRU(int capacity) {
            this.SIZE = capacity;
        }

        public boolean removeEldestEntry(Map.Entry eldest) {
            return size() > SIZE;
        }
    }
}