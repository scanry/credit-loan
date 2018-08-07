package com.sixliu.credit.account.entity;

import com.sixliu.credit.common.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author:MG01867
 * @date:2018年7月12日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 账户安全设置
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SecuritySet extends BaseEntity {

	/** 账户id **/
	private String accountId;

	/** 证件类型 **/
	private int certType;

	/** 证件id **/
	private int certId;

	/** 手机号码 **/
	private String phoneNum;
	
	/** 安全问题 **/
	private String question;
	
	/** 安全回答 **/
	private String answer;
}
