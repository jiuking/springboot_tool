package com.hjc.demo.springboot.init;

import com.google.common.collect.Lists;
import com.hjc.demo.springboot.init.entity.People;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tests {
    public static void main(String[] args) {
        List<People> list = new ArrayList<>();
        People p1 = new People();
        p1.setName("3");


        People p2 = new People();
        p2.setName("4");
        list.add(p1);
        list.add(p2);

        List<String> list1 = new ArrayList<>();
        list1.add("3");
        list1.add("4");
        list1.add("4");
        list1.add("6");
        list1.add("7");
        List<String> list2 = new ArrayList<>();
        list.forEach(temp ->{
            list1.stream().filter(data ->Objects.equals(data, temp.getName())).distinct().forEach(a ->{
                temp.setAge(12);
                System.out.println("a:"+a);
            });
//            System.out.println(temp);
        });
//        list.forEach(System.out::println);

        list = Lists.transform(list,temp->{
            if (temp.getName().equals("3")){
                temp.setAge(21);
            }
            return temp;
        });
        list.forEach(System.out::println);
    }
}
