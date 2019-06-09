package com.hjc.demo.springboot.init;

import com.hjc.demo.springboot.init.config.DruidConfig;
import com.hjc.demo.springboot.init.entity.People;
import com.hjc.demo.springboot.init.interceptor.JpaInterceptor;
import com.hjc.demo.springboot.init.interceptor.MybatisInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author: hjc
 * @Date: 15:42 2018/12/13 0013
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
//@MapperScan("com.hjc.demo.springboot.init.dao")
//@ComponentScan("com.hjc")
//@EnableJpaRepositories
//@EntityScan("com.hjc")
public class SpringbootInitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootInitApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix = "people")
    public People people() {
        return new People();
    }

    @Bean
    public MybatisInterceptor mybatisSqlInterceptor(){
        return  new MybatisInterceptor();
    }

    /*@Bean
    public JpaInterceptor jpaInterceptor() {
        return new JpaInterceptor();
    }*/


}

