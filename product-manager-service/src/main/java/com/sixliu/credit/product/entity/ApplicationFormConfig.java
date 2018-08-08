package com.sixliu.credit.product.entity;

import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品申请表单配置
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplicationFormConfig extends BaseEntity {

	/** 客户id:VARCHAR(36) **/
	private String customerId;

	/** 产品id:VARCHAR(36) **/
	private String productId;

	/** 申请额度:INT(11) **/
	private Integer applyQuota;

	/** 申请贷款期限类型(1:月,2:天):INT(1) **/
	private Integer applyLoanDurationType;

	/** 申请贷款期限(数量):INT(3) **/
	private Integer applyLoanDuration;

	/** 推荐人id:VARCHAR(36) **/
	private String refereeUserId;

	/** 活动id:VARCHAR(36) **/
	private String activitiesId;

}
