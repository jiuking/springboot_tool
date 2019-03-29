package com.hjc.demo.springboot.init;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjc.demo.springboot.init.entity.FetchRuleEntity;

import java.util.ArrayList;
import java.util.List;

public class JSONTest {
    public static void main1(String[] args) {
        /*FetchRuleEntity fetchRuleEntity = new FetchRuleEntity();
        fetchRuleEntity.setChannelSource("现下");
        List<RunFKRuleDetailEntity> list = new ArrayList<>();
        RunFKRuleDetailEntity runFKRuleDetailEntity = new RunFKRuleDetailEntity();
        runFKRuleDetailEntity.setIsRunFk("0");
        runFKRuleDetailEntity.setRunFkKey("sdf");
        runFKRuleDetailEntity.setRunFkSort("1");
        runFKRuleDetailEntity.setRunFkName("跑批1");

        RunFKRuleDetailEntity runFKRuleDetailEntity1 = new RunFKRuleDetailEntity();
        runFKRuleDetailEntity1.setIsRunFk("1");
        runFKRuleDetailEntity1.setRunFkKey("3wwe");
        runFKRuleDetailEntity1.setRunFkSort("0");
        runFKRuleDetailEntity1.setRunFkName("跑批2");
        list.add(runFKRuleDetailEntity);
        list.add(runFKRuleDetailEntity1);
        fetchRuleEntity.setRunFKRuleDetailEntityList(list);
        System.out.println(JSONObject.toJSONString(fetchRuleEntity));*/
    }

    public static void main(String[] args) {
        String rule = "{\"channelSource\":\"现下\",\"runFKRuleDetailEntityList\":[{\"isRunFk\":\"0\",\"runFkKey\":\"sdf\",\"runFkName\":\"跑批1\",\"runFkSort\":\"1\"},{\"isRunFk\":\"1\",\"runFkKey\":\"3wwe\",\"runFkName\":\"跑批2\",\"runFkSort\":\"0\"}]}";
        JSONObject userJson = JSONObject.parseObject(rule);
        FetchRuleEntity fetchRuleEntity = JSONObject.toJavaObject(userJson,FetchRuleEntity.class);
        String str = JSONObject.toJSONString(fetchRuleEntity.getRunFKRuleDetailEntityList());
        System.out.println(str);

        List<FetchRuleEntity.RunFKRuleDetailEntity> list = JSONObject.parseArray(str, FetchRuleEntity.RunFKRuleDetailEntity.class);
        FetchRuleEntity f1 = new FetchRuleEntity();
        f1.setChannelSource("测试");
        f1.setRunFKRuleDetailEntityList(list);
        System.out.println(JSONObject.toJSONString(f1));
    }
}
