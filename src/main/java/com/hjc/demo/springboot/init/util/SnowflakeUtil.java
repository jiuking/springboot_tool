package com.hjc.demo.springboot.init.util;

import cn.hutool.core.lang.Snowflake;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : Administrator
 * @date : 2019/1/31 0031 17:53
 * @description : 雪花算法工具
 */
public class SnowflakeUtil {

    private static final Snowflake SNOWFLAKE = new Snowflake(getWorkId(), getDataCenterId(),true);

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime time = LocalDateTime.now();
        String str = time.format(dateTimeFormatter);
        System.out.println(time);
        System.out.println(str.length());
        System.out.println("1091158363812540460".length());
        System.out.println("1091159516998676539".length());
        System.out.println("9223372036854775808".length());
        System.out.println();
        for (int i = 0; i < 1000; i++) {

            System.out.println(generateId());
        }
    }

    public static String generateId() {
        return String.valueOf(SNOWFLAKE.nextId());
    }

    private static Long getWorkId(){
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for(int b : ints){
                sums += b;
            }
            return (long)(sums % 32);
        } catch (UnknownHostException e) {
            // 如果获取失败，则使用随机数备用
            return RandomUtils.nextLong(0,31);
        }
    }


    private static Long getDataCenterId() {
        int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
        int sums = 0;
        for (int i : ints) {
            sums += i;
        }
        return (long) (sums % 32);
    }
}
