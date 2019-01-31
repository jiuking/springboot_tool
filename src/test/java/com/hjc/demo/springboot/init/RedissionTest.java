package com.hjc.demo.springboot.init;

import com.hjc.demo.springboot.init.util.RedisLockUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Administrator
 * @date : 2019/1/30 0030 17:04
 * @description : Redission测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissionTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisson() {
        String localKey = "localKey";
        RLock lock = RedisLockUtil.lock(localKey);
        System.out.println(Thread.currentThread().getName()+"已加锁");
        new Thread(()->{
            RLock rLock = RedisLockUtil.lock(localKey);
            System.out.println(Thread.currentThread().getName()+"已加锁");
            redisTemplate.opsForValue().set("A",123);
            rLock.unlock();
            System.out.println(Thread.currentThread().getName()+"已解锁");
        }).start();
        redisTemplate.opsForValue().set("A", "SDF");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RedisLockUtil.unlock(lock);
        System.out.println(Thread.currentThread().getName()+"已解锁");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        RedisLockUtil.tryLock("A1",500,10000);
        System.out.println(Thread.currentThread().getName()+"开始");

        System.out.println(Thread.currentThread().getName()+"进行中");
        new Thread(() -> {
            RedisLockUtil.tryLock("A1",500,10000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RedisLockUtil.unlock("A1");
            System.out.println(Thread.currentThread().getName()+"结束");
        }).start();

        RedisLockUtil.unlock("A1");
    }
}
