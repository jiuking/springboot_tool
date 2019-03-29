package com.hjc.demo.springboot.init.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/11/5 15:51
 * @Description: 取数规则
 */
@Data
@NoArgsConstructor
public class FetchRuleEntity implements Serializable {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 渠道来源
     */
    private String channelSource;
    /**
     * 描述
     */
    private String chanSourDesc;
    /**
     * 预跑批风控规则
     */
    private String runFkRuleIa;
    /**
     * 数据有效期
     */
    private String expiryDate;
    /**
     * 取数类型
     */
    private String dataType;
    /**
     * 取数日期时间
     */
    private String dataTime;
    /**
     * 取数周期
     */
    private String dataCycle;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 启用/停用
     */
    private String status;
    /**
     * 删除标记
     */
    private String delFlag;
    /**
     * 取数规则
     */
//    private List<RuleEntity> ruleEntities;
    /**
     * 删除规则细节ID数组
     */
    private String[] delRuleIds;

    private String isDisabled;

    /**
     * 取数后是否自动流入分单池 N：否，Y：是
     */
    private String isAutoIntoAssignPool;

    private String ruleStartTime;

    private String ruleEndTime;

    private String dataValidTime;

    /**
     * 导入时，是否跑批（1，是，0，否）
     */
    private String runFkRuleIaImportStatus;

    /**
     * 取数时，是否跑批（1，是，0，否）
     */
    private String runFkRuleIaFetchStatus;

    /**
     * RULE_APPLY_DEPT_COL 规则适用于区域集合
     */
    private String ruleApplyDeptCol;

    //前端展示页面
    private List<RunFKRuleDetailEntity> runFKRuleDetailEntityList;

    //数据库存储该字段 数据库新增该字段
    private String runFKRuleDetail;

    @Data
    public static class RunFKRuleDetailEntity {
        private String runFkKey;
        private String runFkName;
        private String isRunFk;
        private String runFkSort;
    }

}
