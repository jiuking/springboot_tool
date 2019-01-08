package com.hjc.demo.springboot.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author : Administrator
 * @date : 2019/1/8 0008 11:44
 * @description : 随机数组
 */
public class RandomArrays {

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
