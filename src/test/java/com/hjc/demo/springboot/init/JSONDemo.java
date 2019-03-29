package com.hjc.demo.springboot.init;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Administrator
 * @date : 2019/3/21 0021 09:20
 * @description :
 */
public class JSONDemo {

    public static void main(String[] args) {
        FetchRuleEntity fetchRuleEntity = new FetchRuleEntity();
        fetchRuleEntity.setChanSourDesc("asdf");
        List<RunFKRuleDetailEntity> list = new ArrayList<>();
        RunFKRuleDetailEntity runFKRuleDetailEntity = new RunFKRuleDetailEntity();
        runFKRuleDetailEntity.setRunFkName("测试");
        list.add(runFKRuleDetailEntity);
        fetchRuleEntity.setRunFKRuleDetailEntityList(list);
        String str = JSONObject.toJSONString(fetchRuleEntity);
        System.out.println(str);
        JSONObject jsonObject = JSONObject.parseObject(str);
        List<RunFKRuleDetailEntity> runs = JSONObject.parseArray(jsonObject.getString("runFKRuleDetailEntityList"), RunFKRuleDetailEntity.class);
        System.out.println(JSONObject.toJSONString(runs));
    }

    @Data
    public static class FetchRuleEntity{
        private String chanSourDesc;
        private List<RunFKRuleDetailEntity> runFKRuleDetailEntityList;
    }
    @Data
    public static class RunFKRuleDetailEntity {
        private String runFkKey;
        private String runFkName;
        private String isRunFk;
        private String runFkSort;
    }
}
