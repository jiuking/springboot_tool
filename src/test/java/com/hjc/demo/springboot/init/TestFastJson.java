package com.hjc.demo.springboot.init;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hjc.demo.springboot.init.config.FastJsonRedisSerializer;
import com.hjc.demo.springboot.init.entity.People;

import java.nio.charset.StandardCharsets;

public class TestFastJson {
    public static void main(String[] args) {
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(People.class);
        People p = new People();
        p.setAge(12);
        System.out.println(JSON.toJSONString(p, SerializerFeature.WriteNullStringAsEmpty));
    }
}
