package com.sixliu.credit.product.repository.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
*@author:MG01867
*@date:2018年6月15日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductApplyFlowNodeConfig extends BasePo {

	/** 所属产品id:VARCHAR(36)**/
	private String productId;
		
}