package com.hjc.demo.springboot.init;

import com.hjc.demo.springboot.init.entity.FetchCustBaseInfoDto;
import com.hjc.demo.springboot.init.entity.People;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : Administrator
 * @date : 2018/12/18 0018 16:54
 * @description :
 */
public class Test {

    public static void main(String[] args) {
        List<Set<FetchCustBaseInfoDto>> listP = new ArrayList<>();
        Set<FetchCustBaseInfoDto> set = new HashSet<>();
        FetchCustBaseInfoDto p1 = new FetchCustBaseInfoDto();
        p1.setName("张三");
        p1.setIdCard("asdfasdfas21");
        set.add(p1);
        listP.add(set);

        for (Set<FetchCustBaseInfoDto> temp:listP) {
            Set<FetchCustBaseInfoDto> set1 = listP.get(0);
            System.out.println("是否为同一个对象："+set1.equals(temp));
            final Iterator<FetchCustBaseInfoDto> itr = set1.iterator();
            while (itr.hasNext()) {
                FetchCustBaseInfoDto e = itr.next();
                if (temp.contains(e)) {
                    System.out.println("相等");
                }else {
                    System.out.println("不相等");
                }
                if (set1.contains(e)) {
                    System.out.println("本身相等");
                }
            }
//                System.out.println(temp.iterator().next().equals(result.iterator().next()));
        }

    }

    public static void main2(String[] args) {
        List<People> list = new ArrayList<>();
        People p1 = new People();
        p1.setName("张三");
        p1.setAge(22);
        People p2 = new People();
        p2.setName("李四");
        p2.setAge(23);
        list.add(p1);

        list.add(p2);
        People p3 = new People();
        p3.setName("张三");
        p3.setAge(44);
        list.add(p3);
        People p4 = new People();
        p4.setName(null);
        p4.setAge(12);

        list.add(p4);
        p4 = null;
        System.out.println(list.size());

        List<String> nameList = list.stream().filter(temp->temp.getAge()>0).map(People::getName).filter(temp-> !StringUtils.isEmpty(temp)).collect(Collectors.toList());
        nameList.forEach(System.out::println);
        List<People> list1 = new ArrayList<>();
        list1.add(p3);
        list.removeAll(list1);

        List<String> listStr = new ArrayList<>();
        listStr.add("ABC");
        listStr.add(null);
        listStr.add("DEF");
        listStr.add("             ");
        listStr.forEach(System.out::println);
        System.out.println(listStr.size());
        listStr = listStr.stream().filter(temp -> !StringUtils.isEmpty(temp)).collect(Collectors.toList());
        listStr.forEach(System.out::println);
        System.out.println(listStr.size());
    }

    public static void main1(String[] args) {
        List<People> list = new ArrayList<>();
        People p1 = new People();
        p1.setName("张三");
        p1.setAge(22);
        People p2 = new People();
        p2.setName("李四");
        list.add(p1);
        list.add(p2);
        People p3 = new People();
        p3.setName("张三");
        p3.setAge(44);
        List<People> list1 = new ArrayList<>();
        list1.add(p3);
        list.removeAll(list1);
        System.out.println(list.size());
//        list.stream().reduce()

        List<String> listA = new ArrayList<>();
        listA.add("A");
        listA.add("B");
        listA.add("C");
        listA.add(null);

        List<String> listB = new ArrayList<>();

        listB.add("A");
        listB.add("B");

        List<String> listC = new ArrayList<>();

//        listC.add(null);
        listB.addAll(listC);
        System.out.println("listB"+listB.size());
        listA.removeAll(listB);
        System.out.println("removeA:"+listA.size());
        listA.forEach(System.out::println);
    }
}
