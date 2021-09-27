package com.jason.lee.learn.redis;

import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.util.concurrent.TimeUnit;

/**
 * @author huanli9
 * @description
 * @date 2021/9/26 20:20
 */
public class RedissonTest {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://47.98.183.147:6379");
        singleServerConfig.setPassword("123456");
        RedissonClient redisson = Redisson.create(config);
        RLock redisLock = redisson.getLock("redisLock");
        new Thread(new Worker(redisLock)).start();
        new Thread(new Worker(redisLock)).start();
        new Thread(new Worker(redisLock)).start();
        redisLock.unlock();
    }

    static class Worker implements Runnable {
        RLock redisLock;

        public Worker(RLock redisLock) {
            this.redisLock = redisLock;
        }

        @SneakyThrows
        @Override
        public void run() {
            redisLock.lock(10, TimeUnit.SECONDS);
            Thread.sleep(10000);
        }
    }
}
