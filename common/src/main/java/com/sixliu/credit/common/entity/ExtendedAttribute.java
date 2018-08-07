package com.sixliu.credit.common.entity;


import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 扩展属性配置
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExtendedAttribute extends BaseEntity {
	
	/** 所属者id:VARCHAR(36)**/
	private String ownerId;
	
	/** 扩展属性index**/
	private Integer index;
	
	/** 扩展属性名称:VARCHAR(20)**/
	private String group;
	
	/** 扩展属性名称:VARCHAR(20)**/
	private String name;
	
	/** 扩展属性值:VARCHAR(200)**/
	private String value;
	
	/**是否有效:INT(1)**/
	private Integer takeEffect;
}
