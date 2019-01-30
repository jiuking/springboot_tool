package com.hjc.demo.springboot.init.util;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @author : Administrator
 * @date : 2019/1/30 0030 14:59
 */
public interface DistributedLocker {
    /**
     * 获取redis锁
     * @param lockKey
     * @return
     */
    RLock lock(String lockKey);

    /**
     *
     * @param lockKey
     * @param timeout
     * @return
     */
    RLock lock(String lockKey, int timeout);

    /**
     *
     * @param lockKey
     * @param unit
     * @param timeout
     * @return
     */
    RLock lock(String lockKey, TimeUnit unit, int timeout);

    /**
     *
     * @param lockKey
     * @param unit
     * @param waitTime
     * @param leaseTime
     * @return
     */
    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    /**
     *
     * @param lockKey
     */
    void unlock(String lockKey);

    /**
     *
     * @param lock
     */
    void unlock(RLock lock);
}
