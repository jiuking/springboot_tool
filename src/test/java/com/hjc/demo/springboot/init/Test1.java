package com.hjc.demo.springboot.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Test {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("a");
        list.add("ab");
        list.add("ac");
        List list1 = new ArrayList();

        list1.add("a");

        list.removeAll(list1);

        list.forEach(System.out::println);

        List<Demo> list2 = new ArrayList<>();
        Demo demo = new Test().new Demo();
        demo.setName("a");
        demo.setAge("23");
        Demo demo1 = new Test().new Demo();
        demo1.setAge("32");
        demo1.setName("b");

        list2.add(demo);
        list2.add(demo1);

        List<Demo> list3 = new ArrayList<>();
        Demo demo2 = new Test().new Demo();
        demo2.setName("a");
        demo2.setAge("45");

        list3.add(demo2);

        list2.removeAll(list3);
        list2.forEach(System.out::println);
    }

    class Demo{
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Demo{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Demo demo = (Demo) o;
            return Objects.equals(name, demo.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
