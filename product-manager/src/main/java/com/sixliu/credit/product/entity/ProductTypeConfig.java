package com.sixliu.credit.product.entity;

import com.sixliu.credit.common.entity.BaseEntity;

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
public class ProductTypeConfig extends BaseEntity {

	/** 产品父类型数据id:VARCHAR(36)**/
	private String parentId;
	/** 产品类型名称:VARCHAR(20)**/
	private String name;
}
