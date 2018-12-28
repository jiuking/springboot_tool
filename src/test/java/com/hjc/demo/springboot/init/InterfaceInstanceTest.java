package com.hjc.demo.springboot.init;

import java.util.ArrayList;
import java.util.List;

public class InterfaceInstanceTest implements InterfaceTest {
    @Override
    public <T> T finalHandler(T... obj) {
        System.out.println("asd12fasfqwa");
        return null;
    }

    public static void main(String[] args) {
        List<String> caseIds = new ArrayList<>();
        caseIds.add("A1");
        caseIds.add("A2");
        caseIds.add("A3");
        caseIds.add("A4");
        caseIds.add("A5");
        caseIds.add("A6");
        caseIds.add("A7");
        caseIds.add("A8");
        caseIds.add("A9");
//        caseIds.add("A5");
        List<String> empIds = new ArrayList<>();
        empIds.add("1");
        empIds.add("2");
        empIds.add("3");
        empIds.add("4");
        empIds.add("5");
        empIds.add("6");
        empIds.add("7");
        empIds.add("8");
        empIds.add("9");
        empIds.add("10");
        System.out.println("sdf212:"+9/4+"  " + 9%4);
        new InterfaceInstanceTest().averageAssign(caseIds,empIds);
    }
}
