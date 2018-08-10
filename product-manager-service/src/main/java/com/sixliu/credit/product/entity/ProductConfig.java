package com.sixliu.credit.product.entity;

import java.util.Date;

import com.sixliu.credit.common.constant.LoanTermType;
import com.sixliu.credit.common.entity.BaseEntity;
import com.sixliu.credit.product.CreditApplyMutexType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 贷款产品配置
 * 
 * 贷款产品配置:
 * 	1.基础配置
 * 	2.授信申请配置
 * 	3.额度配置
 *  4.贷款配置
 * 
 *  已经贷款申请拒绝后策略:同类产品永久拒绝，同类产品拒绝后n天(黑名单管理)
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductConfig extends BaseEntity {

	/**产品编码:VARCHAR(20)**/
	private String code;
	
	/**产品名称:VARCHAR(20)**/
	private String name;
	
	/**产品类型:VARCHAR(36)**/
	private String typeId;
	
	/**产品优先级:INT(4)**/
	private Integer priority;
	
	/**授信申请互斥类型:INT(1)**/
	private CreditApplyMutexType creditApplyMutexType;
	
	/**使用黑名单组id**/
	private String useBlacklistGroupId;
	
	/**是否关联客户基础额度:INT(1)**/
	private Boolean linkCustomerBaseQuota;
	
	/**授信申请最大持续月数**/
	private Integer creditApplyMaxMonths;
	
	/**授信申请流程模型id**/
	private String creditApplyFlowModleId;
	
	/**授信申请拒绝影响天数**/
	private Integer creditApplyRejectInfluenceDays;

	/**是否循环额度:INT(1)**/
	private Boolean loopCreditlimit;
	
	/**是否支持提高额度:INT(1)**/
	private Boolean increaseCreditlimit;
	
	/**是否支持降低额度:INT(1)**/
	private Boolean decreaseCreditlimit;
	
	/** 最小授信额度**/
	private Integer minCreditlimit;

	/** 最大授信额度 **/
	private Integer maxCreditlimit;
	
	/**额度有效月数**/
	private Integer creditlimitEffectiveMonths;
	
	/** 最小单笔贷款金额:VARCHAR(20) **/
	private Integer minSingleLoanAmount;

	/** 最大单笔贷款金额:VARCHAR(20) **/
	private Integer maxSingleLoanAmount;
	
	/** 单笔贷款期限类型**/
	private LoanTermType loanTermType;
	
	/** 最小单笔贷款期限(数量):INT(3) **/
	private Integer minSingleLoanTerm;

	/** 最大单笔贷款期限(数量):INT(3) **/
	private Integer maxSingleLoanTerm;
	
	/**贷款申请流程模型id**/
	private String loanFlowModleId;
	
	/**是否有效:INT(1)**/
	private Boolean effective;
	
	/**生效日期:TIMESTAMP**/
	private Date effectiveDate;
	
	/**过期日期:TIMESTAMP**/
	private Date expireDate;
}
