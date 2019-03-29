package com.hjc.demo.springboot.init;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : Administrator
 * @date : 2019/3/20 0020 17:02
 * @description :
 */
public class JSONTest {
    public static void main(String[] args) {
        String resp = "{\"data\": [{\"hitBool\": true,\"ruleGroup\": \"YPPZY\",\"ruleGroupName\": \"预跑批专用规则\",\"ruleGroupWeight\": 100,\"ruleList\": [{\"logicRuleList\": [{\"ruleDescription\": \"电销预跑批表达式一\"}],\"resultCode\": \"200\",\"resultDescription\": \"操作成功\",\"ruleName\": \"电销预跑批规则一\",\"ruleNo\": \"规则编号\",\"ruleWeight\": 100}],\"weightSum\": 100}],\"code\": \"200\",\"msg\": \"操作成功\"}";
        System.out.println(new JSONTest().getResultFromResponse("{\"data\": [{\"hitBool\": true,\"ruleGroup\": \"YPPZY\",\"ruleGroupName\": \"预跑批专用规则\",\"ruleGroupWeight\": 100,\"ruleList\": [{\"logicRuleList\": [{\"ruleDescription\": \"电销预跑批表达式一\"}],\"resultCode\": \"200\",\"resultDescription\": \"操作成功\",\"ruleName\": \"电销预跑批规则一\",\"ruleNo\": \"规则编号\",\"ruleWeight\": 100}],\"weightSum\": 100}],\"code\": \"200\",\"msg\": \"操作成功\"}"));
    }
    String getResultFromResponse(String resp) {
        if (!StringUtils.isNotBlank(resp)) {
            return "fail";
        }
        JSONObject jsonObject;
        try {
            jsonObject = JSONObject.parseObject(resp);
        } catch (Exception e) {
            e.printStackTrace();
            // 解析 json 异常时，返回跑批失败
            return "fail";
        }
        Object code = jsonObject.get(RuleEngineReqDto.CODE);
        if (!RuleEngineReqDto.CALL_SUCCESS.equals(code)) {
            return "fail";
        }
        // 调用成功
        JSONArray data = (JSONArray) jsonObject.get(RuleEngineReqDto.DATA);
        // 筛选出 准入规则
        List<String> collect = data.stream()
                .filter(o -> RuleEngineReqDto.YPPZY.equals(((JSONObject) o).get(RuleEngineReqDto.RULE_GROUP)))
                .map(o -> ((JSONObject) o).get(RuleEngineReqDto.HIT_BOOL).toString())
                .collect(Collectors.toList());

        if (Objects.nonNull(collect)&&collect.size()>0) {
            return Boolean.parseBoolean(collect.get(0)) ? "pass"
                    : "not_pass";
        }
        return "fail";
    }
}
class RuleEngineReqDto {
    public static final String CODE = "code";
    /**
     * 调用接口成功
     */
    public static final String CALL_SUCCESS = "200";
    public static final String DATA = "data";
    public static final String RULE_GROUP = "ruleGroup";
    /**
     * 准入规则组编码值
     */
    public static final String ZRGZ = "ZRGZ";

    public static final String YPPZY = "YPPZY";
    /**
     * 命中标识，true:命中；false:未命中
     */
    public static final String HIT_BOOL = "hitBool";
    public static final String SYSTEM_SERIAL_NO_XD = "XD";
    public static final String CUST_ID = "custId";
    /**
     * 如果 数据库没有分公司类型值，那么按 其他 处理
     */
    public static final String BRANCH_OFFICE_TYPE_OTHER = "其他";
    /**
     * 决策引擎不需要，此字段只为在电销中取值用
     */
    private String custId;
    /**
     * 登录账号
     */
    private String loginAccount;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 身份证号码
     */
    private String customerIdCard;
    /**
     * 手机号码
     */
    private String customerPhone;
    /**
     * 合同编号
     */
    private String contractSerialNo;
    /**
     * 系统编号
     */
    private String systemSerialNo;
    /**
     * 产品编号
     */
    private String productSerialNo;
    /**
     * 测试用
     */
    private String branchOfficeType;
    /**
     * 验证模式(规则组编号)
     */
    private String verifyPattern;

    /**
     * 民族，非必传
     */
    private String customerNation;
    /**
     * 年龄，非必传
     */
    private String customerAge;

}
