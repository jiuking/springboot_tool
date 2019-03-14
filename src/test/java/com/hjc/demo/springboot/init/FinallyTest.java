package com.hjc.demo.springboot.init;

public class FinallyTest {
    public static void main(String[] args) {

        System.out.println(new FinallyTest().test());
        ;
    }

    static int test() {
        int x = 1;
        try {
            x++;
            int a = 1/0;
            return x;
        }catch (Exception e){
            return 4;
        }finally {
            ++x;
//            return x;
        }
    }
}
