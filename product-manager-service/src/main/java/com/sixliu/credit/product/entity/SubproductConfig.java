package com.sixliu.credit.product.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 贷款子产品
 * 
 * 
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class SubproductConfig extends ProductConfig {
	
	/** 父产品id:VARCHAR(36)**/
	private String parentId;
	
	/** 要求共同借款人**/
	private Integer requireCoborrower;
	
	/** 要求需要共同借款人**/
	private Integer requireGuarantor;
	
	/** 最小授信额度:INT(11) **/
	private Integer minCreditQuota;

	/** 最大授信额度:INT(11) **/
	private Integer maxCreditQuota;
}
