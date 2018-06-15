package com.sixliu.credit.product.repository.po;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品类型
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductType extends BasePo {

	/** 产品父类型数据id:VARCHAR(36)**/
	private Long parentId;
	/** 产品类型名称:VARCHAR(20)**/
	private String name;
}
