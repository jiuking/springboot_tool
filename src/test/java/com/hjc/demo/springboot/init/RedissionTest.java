package com.hjc.demo.springboot.init;

import com.hjc.demo.springboot.init.util.RedisLockUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Administrator
 * @date : 2019/1/30 0030 17:04
 * @description : Redission测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissionTest {
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
