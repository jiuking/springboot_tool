package com.hjc.demo.springboot.init.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjc.demo.springboot.init.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Administrator
 * @date : 2018/12/13 0013 15:53
 * @description : 配置查看源码
 */
@RestController
public class PeopleController {

    @Autowired
    private People people;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @GetMapping("getPeopleName")
    public String getPeopleName() {
        System.out.println(people.getName());
//        redisTemplate.opsForSet().add("asdf", people);
        redisTemplate.delete("asdf");
        return people.getName();
    }

    @RequestMapping("getPeople")
        public String getPeople(@RequestBody String jsonParam) {
        People people = new People();
        people.setName("sdf张三");
        people.setAge(12);
        System.out.println(jsonParam);
        return JSON.toJSONString(people);
    }
}
