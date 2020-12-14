package com.jason.lee.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 分布式锁
 */
public class DistributedLock {
    private Logger logger = LoggerFactory.getLogger(DistributedLock.class);
    private final ReentrantLock threadLock = new ReentrantLock();
    private Thread lockHolder = null;
    private long timeOut = 0;
    private String mutex = "redisLock";

    public DistributedLock() {
    }

    /**
     * @param timeOut 超时时间
     * @param mutex   锁名
     */
    public DistributedLock(long timeOut, String mutex) {
        this.timeOut = timeOut;
        this.mutex = mutex;
    }

    public boolean lock(long timeOut, TimeUnit timeUnit) throws Exception {
        this.timeOut = timeOut;
        timeOut = timeUnit.toMillis(timeOut);

        /**锁过期时间*/
        long acquireTime = System.currentTimeMillis() + timeOut;
        threadLock.tryLock(acquireTime, timeUnit);   /**无多大意义*/

        try {
            while (true) {
                boolean hasLock = tryLock();
                if (hasLock) {
                    lockHolder = Thread.currentThread();
                    return true;
                } else if (acquireTime < System.currentTimeMillis()) {
                    break;
                }
                Thread.sleep(1000);
            }
        } finally {
            if (threadLock.isHeldByCurrentThread())
                threadLock.unlock();
        }
        return false;
    }

    private boolean tryLock() {
        long currentTime = System.currentTimeMillis();
        String expire = String.valueOf(timeOut + currentTime);
        if (RedisHelper.setNx(mutex, expire) > 0) {
            //获得锁
            RedisHelper.setExpire(expire);
            return true;
        } else {
            long currentLockTime = RedisHelper.get(mutex);
            /**锁过期*/
            if (Objects.nonNull(currentLockTime) && currentLockTime < currentTime) {
                long oldLockTime = RedisHelper.getSet(mutex, expire);
                if (Objects.nonNull(oldLockTime) && oldLockTime == currentTime) {
                    RedisHelper.setExpire(expire);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean unlock() {
        if (lockHolder == Thread.currentThread()) {
            /**锁没有过期才能释放*/
            if (RedisHelper.get(mutex) > System.currentTimeMillis()) {
                RedisHelper.delete(mutex);
                logger.info("释放[{}]锁成功", mutex);
            }
            lockHolder = null;
            return true;
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    static class RedisHelper {
        private static Jedis jedis = new Jedis("localhost");
        private static String key;
        private static String value;

        public static long setNx(String key, String value) {
            RedisHelper.key = key;
            RedisHelper.value = value;
            return jedis.setnx(key, value);
        }

        public static long get(String key) {
            return Long.valueOf(jedis.get(key));
        }

        public static long getSet(String key, String value) {
            return Long.parseLong(jedis.getSet(key, value));
        }

        public static void delete(String key) {
            jedis.del(key);
        }

        public static void setExpire(String expire) {
            jedis.expire(key, Integer.parseInt(expire));
        }
    }
}
