package com.sixliu.credit.common.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 基础po
 */
@Data
public abstract class BaseEntity {

	/**数据id业务无关性:VARCHAR(36)**/
	private String id;
	
	/**数据备注:VARCHAR(100)**/
	private String remarks;
	
	/**数据版本:INT(11)**/
	private Integer version;
	
	/**数据所有者id:VARCHAR(36)**/
	private String ownerId;
	
	/**数据更新用户id:VARCHAR(36)**/
	private String updateUserId;
	
	/**数据更新日期(每次写操作时赋值):TIMESTAMP**/
	private Date updateDate;
	
	/**数据创建用户id:VARCHAR(36)**/
	private String createUserId;
	
	/**数据创建日期(只在创建时赋值):TIMESTAMP**/
	private Date createDate;
}
