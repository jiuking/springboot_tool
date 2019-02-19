package com.hjc.demo.springboot.init;

/**
 * @author : Administrator
 * @date : 2019/2/15 0015 10:01
 * @description : 测试
 */
public class FinallyTest {
    public static void main(String[] args) {

        System.out.println(new FinallyTest().test());
    }

    static int test()
    {
        int x = 1;
        try {
            x++;
            int a = 1 /0;
            return x;
        } catch (Exception e) {

        } finally {
            ++x;
            return 4;
        }
    }
}
