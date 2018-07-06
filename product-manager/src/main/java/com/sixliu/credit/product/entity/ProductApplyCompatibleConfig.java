package com.sixliu.credit.product.entity;

import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
*@author:MG01867
*@date:2018年6月15日
*@E-mail:359852326@qq.com
*@version:
*@describe 产品申请兼容产品配置
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductApplyCompatibleConfig extends BaseEntity {

	/** 所属产品id:VARCHAR(36)**/
	private String productId;
	
	/** 兼容产品id:VARCHAR(36)**/
	private String compatibleProductId;
}
