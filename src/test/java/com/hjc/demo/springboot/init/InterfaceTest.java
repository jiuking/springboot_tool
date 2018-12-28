package com.hjc.demo.springboot.init;

import com.hjc.demo.springboot.init.util.Iterables;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface InterfaceTest {
    default void prin() {
        System.out.println("asdffff");
    }
    default String averageAssign(List<String> caseIds, List<String> empIds) {
        //客户经理数 步长
        int span = empIds.size();
        //案件数 与客户经理数取整
        int limit = caseIds.size();
        limit = limit % span == 0 ? limit / span : limit / span + 1;

        Stream.iterate(0, n -> n + 1).limit(limit).forEach(a -> {
            System.out.println("=======:a" + a);
            //案件
            int i = 0;
            List<String> list = caseIds.stream().skip(a * span).limit(span).parallel().collect(Collectors.toList());
            System.out.println("list 大小" + list.size());
            Iterables.forEach(list, (index, str) -> {
                empIds.stream().skip(index).limit(1).forEach(temp -> {
                    System.out.println(index + "--->" + temp + " -> " + str);
                });
            });

            System.out.println("--------");
        });
        empIds.stream().limit(1).parallel().forEach(System.out::println);
        finalHandler();
        return null;
    }

    <T> T finalHandler(T ...obj);

}
