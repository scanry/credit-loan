package com.sixliu.credit.account.entity;

import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
*@author:MG01867
*@date:2018年7月12日
*@E-mail:359852326@qq.com
*@version:
*@describe 信用类型账户共享关系
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class CreditAccountShareMap extends BaseEntity {

	/** 主账户Id **/
	private String mainAccountId;
	
	/** 共享账户Id **/
	private String shareaAccountId;
}
