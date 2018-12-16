package com.hjc.demo.springboot.init.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * @author : Administrator
 * @date : 2018/12/13 0013 15:47
 * @description : 查看依赖注解源码
 */
@Data
public class People implements Serializable {

    private String name;

    private Integer age;

}
