package com.hjc.demo.springboot.init;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Administrator
 * @date : 2018/12/28 0028 11:05
 * @description :
 */
public class TestGuava {
    public static void main(String[] args) {
        List<String> ids = new ArrayList<>();
        ids.add("A1");
        ids.add("A2");
        ids.add("A3");
        ids.add("A4");
        ids.add("A5");
        List<String> empIds = new ArrayList<>();
        empIds.add("1");
        empIds.add("2");
        empIds.add("3");
        empIds.add("5");
        empIds.add("4");
        //待分单id
        System.out.println(ids.size() / empIds.size());
        System.out.println(3%2);
        //待分单数大于人员数 均分，然后剩余 随机分。
        if (ids.size() / empIds.size() > 0) {
            System.out.println("asdf");
        }
        //待分单等于人员数 均分

        //待分单小于人员数 随机分
        List<List<String>> noAssignedList = Lists.partition(ids, 3);
        noAssignedList.forEach(temp->{
            System.out.println(temp.size());
            temp.forEach(System.out::println);
        });

        Arrays.stream(randomArray(0, 6, 6)).boxed().forEach(System.out::println);

        List<String> empIds1 = new ArrayList<>();
        empIds1 = empIds1.stream().filter(temp -> !StringUtils.isEmpty(temp)).collect(Collectors.toList());
        System.out.println("大小："+empIds1.size());
        randomStr(empIds);
        String[] s = new String[2];
        s[0] = "123";
        s[1] = "45";

    }

    public static List<String> randomStr(List<String> strings) {
        int[] randomNum = randomArray(0,strings.size()-1,strings.size());
        List list = new ArrayList();
        Arrays.stream(randomNum).boxed().forEach(temp ->{
            list.add(strings.get(temp));
        });
        list.forEach(System.out::println);
        return list;
    }

    /**
     * 随机指定范围内N个不重复的数
     * 在初始化的无重复待选数组中随机产生一个数放入结果中，
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
     * 然后从len-2里随机产生下一个随机数，如此类推
     * @param max  指定范围最大值
     * @param min  指定范围最小值
     * @param n  随机数个数
     * @return int[] 随机数结果集
     */
    public static int[] randomArray(int min,int max,int n){
        int len = max-min+1;
        if(max < min || n > len){
            return null;
        }
        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min+len; i++){
            source[i-min] = i;
        }
        int[] result = new int[n];
        Random rd = new Random();
        int index;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            int i1 = rd.ints(min, (max + 1)).findFirst().getAsInt();
            index = Math.abs(i1 % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }
}
