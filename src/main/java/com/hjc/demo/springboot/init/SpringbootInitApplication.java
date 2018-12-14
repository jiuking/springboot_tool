package com.hjc.demo.springboot.init;

import com.hjc.demo.springboot.init.entity.People;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.beans.ConstructorProperties;

/**
 * @Author: hjc
 * @Date: 15:42 2018/12/13 0013
 */
@SpringBootApplication
public class SpringbootInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootInitApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "people")
    public People people() {
        return new People();
    }

}

