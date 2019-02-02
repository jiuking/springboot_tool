package com.hjc.demo.springboot.init;

public class TestValueCopy {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setName("zhangs");
        System.out.println("p1"+p1);
        Person p2 = p1;
        p2.setName("lis");


        System.out.println("p2"+p2);
        System.out.println("p1"+p1);
    }
}

class Person{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}