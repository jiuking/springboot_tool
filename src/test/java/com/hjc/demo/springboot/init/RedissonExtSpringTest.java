package com.hjc.demo.springboot.init;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author : Administrator
 * @date : 2019/1/31 0031 09:22
 * @description : redisson测试
 */
public class RedissonExtSpringTest {
    public static void main(String[] args) {
        long date = 1288834974657L;
        System.out.println("时间："+new Date(date));
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        System.out.println("时间："+getDateTimeStr());
        long dateTimeLong = getDateTimeStr();
        //分布式自增 设置过期时间为1秒 key 设置为当前时间 R
        String prefixKey = "R";
        RAtomicLong rAtomicLong = redissonClient.getAtomicLong(prefixKey);
        rAtomicLong.set(dateTimeLong);
        String rAtomicStr = String.valueOf(rAtomicLong.getAndIncrement());
        String seri = dateTimeLong + rAtomicStr;
        System.out.println("rAtomicStr"+rAtomicStr);
        System.out.println("最终："+dateTimeLong+rAtomicStr);
        System.out.println("删除"+rAtomicLong.getAndDelete());
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

    private static long getDateTimeStr() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.of(2019,1,2,20,10,3);
        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println("2019 01 02 20 10 03".length());
        System.out.println("=========="+localDateTime1.format(dateTimeFormatter));
        String dateTimeStr = localDateTime.format(dateTimeFormatter)+"0000";
        return Long.parseLong(dateTimeStr);
    }
}
