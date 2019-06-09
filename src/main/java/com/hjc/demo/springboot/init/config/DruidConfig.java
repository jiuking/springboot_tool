package com.hjc.demo.springboot.init.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.jconsole.DruidPlugin;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DruidConfig {

    @Autowired
    private CustomFilter customFilter;

    @Bean
    public DruidDataSource druidDataSource() throws SQLException {
        //Druid 数据源配置
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springbootdb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        //初始连接数(默认值0)
        dataSource.setInitialSize(8);
        //最小连接数(默认值0)
        dataSource.setMinIdle(8);
        //最大连接数(默认值8,注意"maxIdle"这个属性已经弃用)
        dataSource.setMaxActive(32);
//        dataSource.addFilters("customFilter"); customFilter 无效，因这个字符串未定义
        /*StatFilter stat = new StatFilter();
        stat.setLogSlowSql(true);
        stat.setMergeSql(true);
        dataSource.setProxyFilters(Lists.newArrayList(stat));*/
        List<Filter> filters = new ArrayList<Filter>();
        //注入拦截器
        filters.add(customFilter);//new CustomFilter());
        dataSource.setProxyFilters(filters);
        return dataSource;
    }

}
