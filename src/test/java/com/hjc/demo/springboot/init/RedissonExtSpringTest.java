package com.hjc.demo.springboot.init;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author : Administrator
 * @date : 2019/1/31 0031 09:22
 * @description : redisson测试
 */
public class RedissonExtSpringTest {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RLock lock = redissonClient.getLock("A2");
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"已加锁");
        new Thread(() -> {
            RedissonClient redissonClient1 = Redisson.create(config);
            RLock lock1 = redissonClient1.getLock("A2");
            lock1.lock();
            System.out.println(Thread.currentThread().getName()+"已加锁");
            lock1.unlock();
            System.out.println(Thread.currentThread().getName()+"已解锁");
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"等待解锁");
        lock.unlock();
        System.out.println(Thread.currentThread().getName()+"已解锁");
        System.out.println(redissonClient.isShuttingDown());
    }
}
