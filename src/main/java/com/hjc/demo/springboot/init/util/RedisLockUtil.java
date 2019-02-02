package com.hjc.demo.springboot.init.util;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @author : Administrator
 * @date : 2019/1/30 0030 14:56
 * @description : redis锁
 */
public class RedisLockUtil {

    private static DistributedLocker redissLock;

    public static void setDistributedLocker(DistributedLocker redissLock) {
        RedisLockUtil.redissLock = redissLock;
    }

    /**
     * 加锁
     * @param lockKey
     * @return
     */
    public static RLock lock(String lockKey) {
        return redissLock.lock(lockKey);
    }

    /**
     * 释放锁
     * @param lockKey
     */
    public static void unlock(String lockKey) {
        redissLock.unlock(lockKey);
    }

    /**
     * 释放锁
     * @param lock
     */
    public static void unlock(RLock lock) {
        redissLock.unlock(lock);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param timeout 超时时间   单位：秒
     */
    public static RLock lock(String lockKey, int timeout) {
        return redissLock.lock(lockKey, timeout);
    }

    /**
     * 带超时的锁
     * @param lockKey
     * @param unit 时间单位
     * @param timeout 超时时间
     */
    public static RLock lock(String lockKey, TimeUnit unit , int timeout) {
        return redissLock.lock(lockKey, unit, timeout);
    }

    /**
     * 尝试获取锁
     * @param lockKey
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public static boolean tryLock(String lockKey, int waitTime, int leaseTime) {
        return redissLock.tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
    }

    /**
     * 尝试获取锁
     * @param lockKey
     * @param unit 时间单位
     * @param waitTime 最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public static boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) {
        return redissLock.tryLock(lockKey, unit, waitTime, leaseTime);
    }
}
