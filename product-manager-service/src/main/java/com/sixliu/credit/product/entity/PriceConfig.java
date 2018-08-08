package com.sixliu.credit.product.entity;


import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年7月30日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 定价配置
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PriceConfig extends BaseEntity {

	/** 定价名称**/
	private Integer name;
	
	/** 最小单笔贷款金额:VARCHAR(20) **/
	private Integer minSingleLoanAmount;

	/** 最大单笔贷款金额:VARCHAR(20) **/
	private Integer maxSingleLoanAmount;

	/** 单笔贷款期限类型(1:月,2:天):INT(1) **/
	private Integer creditDurationType;

	/** 最小单笔贷款期限(数量):INT(3) **/
	private Integer minSingleCreditDuration;

	/** 最大单笔贷款期限(数量):INT(3) **/
	private Integer maxSingleCreditDuration;
	
	/** 还款策略**/
	private String repaymentStrategy;
	
	/** 手续费策略**/
	private String poundageStrategy;
	
	/** 手续费率**/
	private Double poundageRate;
	
	/** 利率**/
	private Double interestRate;
}
