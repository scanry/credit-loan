package com.sixliu.credit.product.entity;


import com.sixliu.credit.common.po.BasePo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 贷款产品
 * 
 * 贷款产品配置:
 * 	1.基础配置
 * 	2.申请配置
 * 	3.账户配置
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductConfigLog extends BasePo {

	/**原始产品编码:VARCHAR**/
	private String originalId;
}
