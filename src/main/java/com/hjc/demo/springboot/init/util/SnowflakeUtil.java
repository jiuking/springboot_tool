package com.hjc.demo.springboot.init.util;

import cn.hutool.core.lang.Snowflake;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author : Administrator
 * @date : 2019/1/31 0031 17:53
 * @description : 雪花算法工具
 */
public class SnowflakeUtil {

    private static final Snowflake SNOWFLAKE = new Snowflake(getWorkId(), getDataCenterId(),true);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {

            System.out.println(generateId());
        }
    }

    public static long generateId() {
        return SNOWFLAKE.nextId();
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
