package com.sixliu.credit.quota;


import lombok.Data;

/**
*@author:MG01867
*@date:2018年8月6日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Data
public class CreditlimitDTO {

	/** 是否有效:INT(1) **/
	private Boolean effective;
	
	/**剩余额度**/
	private Double surplusAmount;
}
