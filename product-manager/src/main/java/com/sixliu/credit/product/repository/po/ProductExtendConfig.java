package com.sixliu.credit.product.repository.po;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品扩展配置
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductExtendConfig extends BasePo {
	
	/** 所属产品id:VARCHAR(36)**/
	private String productId;
}
