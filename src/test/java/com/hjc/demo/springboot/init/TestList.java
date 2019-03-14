package com.hjc.demo.springboot.init;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        test(list);
        list.forEach(System.out::println);

    }

    static List<String> test(List<String> list) {
        list = list.stream().filter(temp -> temp.equals("c")).map(String::new).collect(Collectors.toList());
        list.forEach(System.out::println);
        return list;
    }
}
