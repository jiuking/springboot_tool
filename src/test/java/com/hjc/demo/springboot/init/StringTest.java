package com.hjc.demo.springboot.init;

/**
 * @author : Administrator
 * @date : 2019/2/19 0019 14:48
 * @description : string测试
 */
public class StringTest {
    static final String s2 = "a";
    static final String s3 = "bc";
    public static void main(String[] args) {
        String a = "abc";
        String b = new String("abc").intern();
        System.out.println(a == b);

        final String s1 = "abc";

        String s4 = s2 + s3;
        System.out.println(s1 == s4);

        String s51 = new String("1");//
        String s5 =  new String("A") + new String("B");
        String intern = s51.intern();

        System.out.println((s51 == intern) +"abc");


        String s6 = "AB";
        s5.intern();

        System.out.println(s5 == s6);
        System.out.println(s51 == s6);
        s5 = null;
        System.out.println(s6);
    }
}
