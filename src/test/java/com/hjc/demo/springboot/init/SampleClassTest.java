package com.hjc.demo.springboot.init;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Administrator
 * @date : 2019/2/25 0025 10:23
 * @description : 相同类比较
 */
public class SampleClassTest {
    public static void main(String[] args) {
        List<Class1> list1 = new ArrayList<>();
        List<Class2> list2 = new ArrayList<>();
        Class1 class1 = new SampleClassTest().new Class1();
        Class2 class2 = new SampleClassTest().new Class2();
        list1.add(class1);
        class1.setName1("12");
        list2.add(class2);
        class2.setName2("12");
        System.out.println(list1.contains(list2.get(0)));
        list1.forEach(temp -> {
            temp.setName1("ABC");
        });
        list1.forEach(System.out::println);
        list1 = Lists.transform(list1,temp->{
            temp.setName1("DEF");
        return temp;});
        list1.forEach(System.out::println);//9490.3
        Lists.transform(list1, temp -> {
            temp.setName1("ZZZ");
            return temp;
        });
        list1.forEach(System.out::println);
    }




    class Class1{
        private String name1;

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        @Override
        public String toString() {
            return "Class1{" +
                    "name1='" + name1 + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Class1 class1 = (Class1) o;
            return Objects.equal(name1, class1.name1);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name1);
        }
    }

    class Class2{
        private String name2;
        private int age;

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
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
            Class2 class2 = (Class2) o;
            return Objects.equal(name2, class2.name2);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name2);
        }
    }
}
