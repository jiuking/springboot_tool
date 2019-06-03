package com.hjc.demo.springboot.init.jpaListener;

import org.springframework.core.annotation.AnnotatedElementUtils;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjectListener {
    @PostPersist
    public void postPersist(Object source){
        System.out.println("@PostPersist：" + source);
    }

    @PostLoad
    public void postLoad(Object object) throws IllegalAccessException {
        getTableName(object.getClass());
        System.out.println("@PostLoad：" + object);
        getTableColumn(object);
    }

    @PostUpdate
    public void postUpdate(Object object) {
        object.getClass();
        System.out.println("@PostLoad：" + object+"==");
    }

    public void getTableName(Class clazz) {
        Table table = (Table) clazz.getAnnotation(Table.class);
        System.out.println(table.name());
    }

    public void getTableColumn(Object obj) throws IllegalAccessException {
        Class clazz = obj.getClass();
        // 循环遍历字段，获取字段相应的属性值
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {//向上循环  遍历父类
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                // 假设不为空。设置可见性，然后返回
                field.setAccessible(true);
                String filed = underline(field.getName());
                System.out.println(filed + ":" + field.get(obj));
            }
        }
    }

    @PostRemove
    public void postRemove(Object object) {
        System.out.println("@postRemove:" + object);
    }

    static Pattern pattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    public String underline(String str) {

        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if(matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb,"_"+matcher.group(0).toLowerCase());
            //把之后的也添加到StringBuffer对象里
            matcher.appendTail(sb);
        }else {
            return sb.toString();
        }
        return underline(sb.toString());
    }

    public static void main(String[] args) {
//        System.out.println(underline("userName"));
    }
}
