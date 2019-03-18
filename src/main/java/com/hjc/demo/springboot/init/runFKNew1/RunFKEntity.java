package com.hjc.demo.springboot.init.runFKNew1;

import lombok.Data;

@Data
public class RunFKEntity {

    private String login_code;
    private String login_pw;
    //同盾风险信息
    private String contract_no;
    private String account_name;
    private String id_number;
    private String account_mobile;
    private String account_email;
    private String sys_type;
    private String resp_detail_type;
//汇法网黑名单
    private String idNumber;
    private String name;
    //数据源
    private String data_source;

//    获取前海黑名单客户接口
//    private String name;
    private String idType;

    private String idNo;

    private String reasonCode;
    private String loan_contract_no;
    private String phone;
//    private String data_source;

//宜信阿福-存储用户基本信息接口
    private String contractNo;
//    private String idNo;
//    private String name;
    private String dataSource;
    private String queryReason;
//百融数据
//    private String loan_contract_no;
    /**
     * 身份证号
     */
    private String id;

    /**
     * 电话号码
     */
    private String cell;

//    private String name;
    /**
     * 接入方式
     */
    private String access_type;
    /**
     * 设备指纹
     */
    private String gid;
}