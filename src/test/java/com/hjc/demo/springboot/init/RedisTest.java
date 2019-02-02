package com.hjc.demo.springboot.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : Administrator
 * @date : 2018/12/27 0027 16:13
 * @description : redis测试
 */
public class RedisTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.forEach(System.out::println);
        Map<String, String> map = list.stream().collect(Collectors.toMap(b -> b, a -> a, (k1, k2) -> k2));
        map.forEach((k,v)->{
            System.out.println(k+":"+v);
        });
    }
}
