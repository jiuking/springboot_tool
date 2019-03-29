package com.hjc.demo.springboot.init;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用Redisson生成唯一日期流水号
 *
 * @author : Administrator
 * @date : 2019/2/2 0002 11:45
 * @description :
 */
public class GenerateIdByRedissonTest {
    static RedissonClient redissonClient = Redisson.create();
    private static String dateTimePattern = "yyyyMMddHHmmss";
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
    private static String key = "R$NowDateTime";
    public static void main(String[] args) {
        System.out.println(String.valueOf(getNowDateTimeStr()).length());
        AtomicInteger a = new AtomicInteger();
        System.out.println(a.incrementAndGet());
//        RAtomicLong rAtomicLong = redissonClient.getAtomicLong(prefixKey);
//
//        //先获取在自增
//        System.out.println("初始化大小："+rAtomicLong.getAndIncrement());
        for (int i = 0; i < 1000; i++) {
            System.out.println("end---------"+generateIdNowDateTimeByRedis()+i);
        }
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redissonClient.shutdown();
    }

    /**
     *
     * 以秒为区分，递增。例如：
     * 2019 02 02 12 37 20 0000
     * 2019 02 02 12 37 20 0001
     * 2019 02 02 12 37 20 0002
     * 。。。。
     * 2019 020 2 12 37 21 0000
     * 超过则添加后缀E
     *
     * @return
     */
    public static String generateIdNowDateTimeByRedis() {
        RLock lock = redissonClient.getLock("RLock$NowDateTimeId");
        try {
            lock.lock();
            //标志为是否一秒钟内自增生成大于9999个数。true：未生成超过9999，false：生成超过9999
            boolean notExceed9999Flag = true;
            RAtomicLong rAtomicLong = redissonClient.getAtomicLong(key);
            //判断是否相等，相等：+1，不等：设值。不可行需多加判断前14位是否一致，不一致则重新设置，否则在此基础上+1.局限性，1秒内只能生成10的4次方个数字
            //考虑key 设值为前14位，value设值为后4位。注意需要删除前一秒的key。否则每一秒都会生成一个key，考虑用lua基本去实现。返回String同时也需要加锁
            String rAtomicStr = String.valueOf(rAtomicLong.getAndIncrement());
            System.out.println("start========" + rAtomicStr);
            String nowDateTime = getNowDateTimeStr();
            //没有key的情况，初始化会是0.
            if (rAtomicStr.equals("0")) {
                rAtomicStr = nowDateTime + "0000";
                rAtomicLong.set(Long.parseLong(rAtomicStr));
                return rAtomicStr;
            }
            //相等，说明是同一秒钟下的自增，则直接返回，情况2：同一秒钟下，若自增已超过4位数(9999)，则，自增的数据大于当前时间：加后缀E直接返回。
            long ex014 = Long.parseLong(rAtomicStr.substring(0, 14));
            if (rAtomicStr.substring(0, 14).equals(nowDateTime) && notExceed9999Flag) {
                return rAtomicStr;
            } else if (ex014 > Long.parseLong(nowDateTime)) {
                notExceed9999Flag = false;
                return rAtomicStr + "E";//同一秒钟下，后4位自增生成已超过9999，末尾加E
            }
            rAtomicStr = nowDateTime + "0000";
            rAtomicLong.set(Long.parseLong(rAtomicStr));
            //先获取在自增，所以先自增一次
            rAtomicLong.getAndIncrement();
            return rAtomicStr;
        } catch (Exception e) {
            return null;
        } finally {
            lock.unlock();
        }

    }

    /**
     * 获取以当前时间生成18位长整数
     *
     * @return 获取以当前时间生成18位长整数
     */
    public static String getNowDateTimeStr() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(dateTimeFormatter);
    }
}
