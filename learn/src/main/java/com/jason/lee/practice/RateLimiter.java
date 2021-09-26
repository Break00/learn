package com.jason.lee.practice;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author huanli9
 * @description 限流器
 * @date 2021/7/6 9:29
 */
public class RateLimiter {
    public static void main(String[] args) throws InterruptedException {
        LeakyBucket bucket = new LeakyBucket(10, 1);
        while (true) {
            for (int i = 0; i < 1000; i++) {
                new Thread(() -> {
                    if (bucket.grant()) {
                        System.out.println("执行业务: " + Thread.currentThread().getName());
                    } else {
//                        System.out.println("限流：" + Thread.currentThread().getName());
                    }
                }).start();
            }
            Thread.sleep(1000);
        }
    }

}

/**
 * 固定窗口限流（计数器限流）
 */
class Counter {
    /**
     * 请求数
     */
    private static Long count = 10L;
    /**
     * 时间（秒）
     */
    private static final long INTERVAL = 1000;

    private static long start = System.currentTimeMillis();
    private static AtomicLong atomicLong = new AtomicLong();

    public static boolean tryOut() {
        long now = System.currentTimeMillis();
        if (now > start + INTERVAL) {
            atomicLong.set(0);
            start = System.currentTimeMillis();
        }
        if (atomicLong.get() < count) {
            atomicLong.incrementAndGet();
            return true;
        }
        return false;
    }
}

/**
 * 滑动窗口
 */
class SlidingWindow {

    private static List<Long> record = new LinkedList<>();

    /**
     * @param limit      请求数
     * @param timeWindow 时间窗
     * @return
     */
    public static synchronized boolean tryOut(int limit, long timeWindow) {
        long nowTime = System.currentTimeMillis();
        if (record.size() < limit) {
            record.add(0, nowTime);
            return true;
        }

        long startTime = record.get(limit - 1);
        if (nowTime - startTime <= timeWindow) {
            return false;
        } else {
            record.remove(limit - 1);
            record.add(0, nowTime);
            return true;
        }
    }
}

/**
 * 漏桶
 */
class LeakyBucket {
    public long startTime;
    public long capacity; // 桶的容量
    public long rate; // 水漏出的速度
    public long water; // 当前水量(当前累积请求数)

    public LeakyBucket(long capacity, long rate) {
        this.rate = rate / 1000;
        this.capacity = capacity;
        this.startTime = System.currentTimeMillis();
    }

    public boolean grant() {
        long now = System.currentTimeMillis();
        water = Math.max(0, water - (now - startTime) * rate);

        startTime = now;
        if ((water + 1) < capacity) {
            water += 1;
            return true;
        } else {
            return false;
        }
    }
}

/**
 * 令牌桶
 */
class TokenBucket {
    public long startTime;
    public long capacity; // 桶的容量
    public long rate; // 令牌放入速度
    public long tokens; // 当前令牌数量

    public TokenBucket(long capacity, long rate) {
        this.rate = rate / 1000;
        this.capacity = capacity;
        this.startTime = System.currentTimeMillis();
    }

    public boolean grant() {
        long now = System.currentTimeMillis();
        // 添加令牌
        tokens = Math.min(capacity, tokens + (now - startTime) * rate);
        startTime = now;
        if (tokens < 1) {
            return false;
        } else {
            // 领取令牌
            tokens -= 1;
            return true;
        }
    }
}






