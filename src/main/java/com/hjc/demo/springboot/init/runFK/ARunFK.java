package com.hjc.demo.springboot.init.runFK;

import java.util.HashMap;
import java.util.Map;

public class ARunFK extends AbstractRunFk {

    @Override
    protected boolean isRunFK(RunFKEntity trouble) {
        Map<String, String> map = new HashMap<>();
        map.put("login_code", "");

        System.out.println("ARunFK");
        return true;
    }


    @Override
    protected void runFK(Object trouble) {
        System.out.println("跑批");
        //跑批未通过设置下一节点为null
//        setNext(null);
    }

    private void assembleRequestEntity(Map<String,String> map) {

    }
}
