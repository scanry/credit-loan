package com.sixliu.credit.product.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 贷款产品配置副本
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductConfigCopy extends ProductConfig {

	/**原始产品编码:VARCHAR**/
	private String originalId;

}
