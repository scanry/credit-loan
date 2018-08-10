package com.sixliu.credit.product;

import lombok.Data;

/**
 * @author:MG01867
 * @date:2018年8月6日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@Data
public class ProductInnerDTO {

	/** 产品编码:VARCHAR(20) **/
	private String id;
	
	/** 产品编码:VARCHAR(20) **/
	private String code;

	/** 产品名称:VARCHAR(20) **/
	private String name;

	/** 产品类型:VARCHAR(36) **/
	private String typeId;

	/** 产品优先级:INT(4) **/
	private Integer priority;

	/** 授信申请互斥类型:INT(1) **/
	private CreditApplyMutexType creditApplyMutexType;

	/** 黑名单组**/
	private String blacklistGroupId;
	
	/** 产品额度id**/
	private String creditlimitId;

	/** 是否关联客户基础额度:INT(1) **/
	private Integer linkCustomerBaseQuota;

	/** 授信申请最大持续月数 **/
	private Integer creditApplyMaxMonths;

	/** 授信申请流程模型id **/
	private String creditApplyFlowModleId;

	/** 授信申请拒绝影响天数 **/
	private Integer creditApplyRejectInfluenceDays;

	/** 是否循环额度:INT(1) **/
	private Integer loopQuota;

	/** 是否支持提高额度:INT(1) **/
	private Integer increaseQuota;

	/** 是否支持降低额度:INT(1) **/
	private Integer decreaseQuota;

	/** 最小授信额度 **/
	private Integer minCreditQuota;

	/** 最大授信额度 **/
	private Integer maxCreditQuota;

	/** 额度有效月数 **/
	private Integer quotaEffectiveMonths;

	/** 最小单笔贷款金额:VARCHAR(20) **/
	private Integer minSingleLoanAmount;

	/** 最大单笔贷款金额:VARCHAR(20) **/
	private Integer maxSingleLoanAmount;

	/** 单笔贷款期限类型 **/
	private LoanTermType singleLoanTermType;

	/** 最小单笔贷款期限(数量):INT(3) **/
	private Integer minSingleLoanTerm;

	/** 最大单笔贷款期限(数量):INT(3) **/
	private Integer maxSingleLoanTerm;

	/** 贷款申请流程模型id **/
	private String loanFlowModleId;

	/** 是否有效:INT(1) **/
	private Boolean effective;
}
