package com.hjc.demo.springboot.init.runFKNew1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hjc.demo.springboot.init.runFK.AbstractRunFk;
import com.hjc.demo.springboot.init.runFK.RunFKEntity;
import com.hjc.demo.springboot.init.runFKNew.FilterChain;
import com.hjc.demo.springboot.init.util.HttpClientPoolUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TongDunFraudRunFKFilter implements RunFKFilter {

    @Override
    public void doFilter(RunFKEntity runFKEntity, FilterChain chain) {

    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
//        map.put("login_code", "");
        System.out.println(JSONObject.toJSONString(map));
        String rul = JSONObject.toJSONString(map);
        if (Objects.nonNull(rul)) {
            JSONObject jsonObject = JSON.parseObject(rul);
            System.out.println("=="+jsonObject.get("login_code"));
        }
    }

    private void assembleRequestEntity(Map<String, String> map,RunFKEntity trouble) {
        //登录名
        map.put("login_code", "");
        //密码
        map.put("login_pw", "");
        //合同号
        map.put("contract_no", "");
        //贷款人姓名
        map.put("account_name", "");
        //贷款人身份证号
        map.put("id_number", "");
        //手机号
        map.put("account_mobile", "");
        //邮箱
        map.put("account_email", "");
        //进件类型
        map.put("sys_type", "");
        //设备指纹类型
        map.put("resp_detail_type", "");
    }
}
