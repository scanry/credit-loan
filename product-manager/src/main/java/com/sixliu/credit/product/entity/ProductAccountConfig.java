package com.sixliu.credit.product.entity;

import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品贷款账户配置
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductAccountConfig extends BaseEntity {

	/** 所属产品id:VARCHAR(36)**/
	private String productId;
	
	/** 账户类型:INT(2)**/
	private Integer type;
	
	/** 是否受父级账户限制(1是;0否):INT(1) **/
	private Integer limitByParentAccount;

	/** 是否循环额度(1是;0否):INT(1) **/
	private Integer loopQuota;
	
	/** 是否支持提升额度(1是;0否):INT(1) **/
	private Integer riseQuota;

	/** 最小授信额度:INT(11) **/
	private Integer minCreditQuota;

	/** 最大授信额度:INT(11) **/
	private Integer maxCreditQuota;

	/** 最小单笔贷款金额:VARCHAR(20) **/
	private Integer minSingleLoanAmount;

	/** 最大单笔贷款金额:VARCHAR(20) **/
	private Integer maxSingleLoanAmount;
	
	/** 单笔贷款期限类型(1:月,2:天):INT(1) **/
	private Integer minSingleLoanDurationType;
	
	/** 最小单笔贷款期限(数量):INT(3) **/
	private Integer minSingleLoanDuration;

	/** 最大单笔贷款期限(数量):INT(3) **/
	private Integer maxSingleLoanDuration;
}
