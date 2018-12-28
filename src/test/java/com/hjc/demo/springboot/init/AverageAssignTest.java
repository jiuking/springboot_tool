package com.hjc.demo.springboot.init;

import com.hjc.demo.springboot.init.util.Iterables;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : Administrator
 * @date : 2018/12/28 0028 15:19
 * @description : 平均分单测试类
 */
public class AverageAssignTest {
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
//        empIds.add("10");
        averageAssign(caseIds, empIds);
        System.out.println("sdf212:"+9/10);
    }

    /**
     * 按电销客户经理来平均分单
     * a）若每人分单量＜1 （比如8单分10个人，平均分单量为0.8），则按1单随机分配给选中的客户经理，剩下未分配到的客户经理则不再进行分配；
     * b）若每人分单量≥1（比如20单分7个人，平均分单量为2.8），则向下取整，选中的客户经理先按2单进行分配，剩下的6单再按1单随机分配给选中的客户经理；
     * 以此类推
     * @param caseIds 待分配案件id
     * @param empIds 电销人员id
     * @return
     */
    public static List<CaseEntity> averageAssign(List<String> caseIds,List<String> empIds) {
        //客户经理数 步长
        int span = empIds.size();
        //案件数 与客户经理数取余
        int limit = caseIds.size() / span + 1;
//        switch (limit) {
//            case 0:
//                limit = 1;
//        }
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(a -> {
            System.out.println("=======:a"+a);
            //案件
            int i = 0;
            List<String> list = caseIds.stream().skip(a * span).limit(span).parallel().collect(Collectors.toList());
            System.out.println("list 大小" +list.size());
            Iterables.forEach(list, (index, str) -> {
                empIds.stream().skip(index).limit(1).forEach(temp->{System.out.println(index+"--->"+temp + " -> " + str);});
            });

            System.out.println("--------");
        });
        empIds.stream().limit(1).parallel().forEach(System.out::println);
        return null;
    }
    
    @Data
    class CaseEntity {
        private String id;
        private String custId;
        private String histTelCnt;
        private String currentTelCnt;
        private String telStatus;
        private String isInAssPool;
        private Date intoAssPoolTime;
        private Date assTime;
        private String handler;
        private String latestCallStatus;
        private String latestCallResult;
        private String createBy;
        private Date createTime;
        private String updateBy;
        private Date updateTime;
        private String delFlag;

        public static final String CUST_ID = "cust_id";
        /**
         * 是否进件
         */
        private String isEntry;
        /**
         * 进件时间
         */
        private Date entryTime;
        /**
         * 合同号
         */
        private String loanNo;
        /**
         * 电销案件是否已读
         */
        private String readCase;
        /**
         * 已进件案件是否已读
         */
        private String readEntry;
        /**
         * 预约跟进案件是否已读
         */
        private String readAppt;
        /**
         * 预约时间
         */
        private Date apptTime;
        /**
         * 追踪有效期
         */
        private String FuValidDays;
        /**
         * 签约放弃状态（只针对门店签约放弃 有效：20代表放弃）
         */
        private String auditStatus;
        /**
         * 签约放弃时间
         */
        private Date auditTime;
    }
}
