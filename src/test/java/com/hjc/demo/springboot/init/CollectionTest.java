package com.hjc.demo.springboot.init;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Administrator
 * @date : 2019/2/18 0018 14:21
 * @description : 集合测试
 */
public class CollectionTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.forEach((k,v)->{
            System.out.println(k+" "+v);
        });

        List<Te> list1 = new ArrayList<>();
        List<Te> list2 = new ArrayList<>();
        Te te1 = new Te();
        te1.setName("a");
        te1.setAge(1);
        Te te2 = new Te();
        te2.setName("b");
        te2.setAge(2);
        Te te3 = new Te();
        te3.setName("c");
        te3.setAge(3);
        list1.add(te1);
        list1.add(te2);
        list1.add(te3);

        Te te11 = new Te();
        te11.setName("c");
        te11.setAge(5);
        Te te12 = new Te();
        te12.setName("d");
        Te te13 = new Te();
        te13.setName("e");
        list2.add(te11);
        list2.add(te12);
        list2.add(te13);
        list1.stream().filter(s1 -> list2.stream().anyMatch(s2 -> s1.equals(s2))).forEach(temp ->{
            temp.setAge(4);
        });
        list1.forEach(System.out::println);
        System.out.println("========");
        list1.forEach(temp ->{
            for (Te t: list2) {
                if (temp.equals(t)){
                    temp.setAge(t.getAge());
                    break;
                }
            }
        });
        list1.forEach(System.out::println);
    }

    static class Te{
        private String name;

        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Te te = (Te) o;
            return Objects.equal(name, te.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }

        @Override
        public String toString() {
            return "Te{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
