package com.hjc.demo.springboot.init.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author : Administrator
 * @date : 2018/11/2 0002 14:18
 * @description : 客户基本信息。注意此 类只适用于公共库取数sql映射的实体
 */
@Data
public class FetchCustBaseInfoDto {
    /**
     * 客户ID
     */
    private String id;
    /**
     * 中央审批客户id
     */
    private String clientId;
    /**
     * 客户姓名
     */
    private String name;
    /**
     * 客户性别
     */
    private String sex;
    /**
     * 客户年龄
     */
    private String age;
    /**
     * 客户所在城市，先居住地
     */
    private String city;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 联系电话1
     */
    private String clientPhoneOne;
    /**
     * 联系电话2
     */
    private String clientPhoneTwo;
    /**
     * 公司名称
     */
    private String compName;
    /**
     * 工作省
     */
    private String compProv;
    /**
     * 工作市
     */
    private String compCity;
    /**
     * 工作县区
     */
    private String compDictCountry;
    /**
     * 工作电话
     */
    private String compPhone;
    /**
     * 公司地址
     */
    private String compAddr;

    /**
     * 工作职位
     */
    private String job;

    /**
     * 月收入下限
     */
    private String monIncomeMin;
    /**
     * 月收入上限
     */
    private String monIncomeMax;

    /**
     * 创建时间
     */
    private Date createTime;

    private List<ContactInfoDto> contactInfoLists;

    private List<LoanInfoDto> loanInfoLists;


    /**
     * 联系人信息
     */
    @Data
    public static class ContactInfoDto {
        private String contactMobile;
        private String contactTel;
    }

    /**
     * 借款信息
     */
    @Data
    public static class LoanInfoDto {
        /**
         * 合同号
         */
        private String contractNo;

        /**
         * 产品
         */
        private String product;
        /**
         * 终审额度
         */
        private String loanFinalAmt;
        /**
         * 贷款合同总金额
         */
        private String loanContAmt;
        /**
         * 贷款期数
         */
        private String loanTerm;
        /**
         * 满标日
         */
        private String successDate;
        /**
         * 已还期数
         */
        private String paidTerm;
        /**
         * 月还款额
         */
        private String monthRepayAmt;
        /**
         * 提前结清金额
         */
        private String iaSettleAmt;
        /**
         * 实际到账金额
         */
        private String actuAccountAmt;
        /**
         *合同创建日期
         */
        private Date contractDate;
        /**
         * 业务线（系统编号）
         */
        private String serviceLine;
        /**
         *分公司类型
         */
        private String compCategory;


       /* @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            LoanInfoDto that = (LoanInfoDto) o;
            return Objects.equals(contractNo, that.contractNo);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(contractNo);
        }*/
    }

    /**
     * important 任何时候只要a.equals(b),那么a.hashCode()必须和b.hashCode()相等
     */
   /* @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FetchCustBaseInfoDto that = (FetchCustBaseInfoDto) o;
        // 身份证相同 或者 客户姓名+客户电话相同 则认为为相等。
        // 身份证为第一要素，身份不为空时,相同则同，身份为空则判断姓名+电话号码
        if (idCard != null) {
            return Objects.equals(idCard, that.idCard);
        }
        //电话号码为空则表示同一条数据
//        if (clientPhoneOne != null) {
        return Objects.equals(name, that.name) &&
                Objects.equals(clientPhoneOne, that.clientPhoneOne);
//        }
//        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        return this.getIdCard() != null ? (31 * result + this.getIdCard().hashCode())
                : (31 * result + (this.getName() == null ? 0 : this.getName().hashCode())
                + (this.getClientPhoneOne() == null ? 0 : this.getClientPhoneOne().hashCode()));
    }*/
}
