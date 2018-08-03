package com.sixliu.credit.product.entity;

import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年7月30日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 额度配置
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QuotaConfig extends BaseEntity {
	
	/** 最小授信额度:INT(11) **/
	private Integer minCreditQuota;

	/** 最大授信额度:INT(11) **/
	private Integer maxCreditQuota;
}
