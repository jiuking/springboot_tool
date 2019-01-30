package com.hjc.demo.springboot.init;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : Administrator
 * @date : 2019/1/22 0022 11:04
 * @description : jdk8循环
 */
public class ListForEachTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> it = list.iterator();
        list.remove("3");
        list.removeAll(list);
        while (it.hasNext()) {
            list.forEach(temp ->{
                if (temp.equals("2")) {
                    it.remove();
                    return;
                }
                System.out.println(temp);
            });
        }
        System.out.println(null == null);
        System.out.println((null == null)+"asdf:");
    }
}
