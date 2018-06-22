package com.sixliu.credit.product.po;

import com.sixliu.credit.common.po.BasePo;

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
public class ProductApplyCompatibleConfig extends BasePo {

	/** 所属产品id:VARCHAR(36)**/
	private String productId;
	
	/** 兼容产品id:VARCHAR(36)**/
	private String compatibleProductId;
}
