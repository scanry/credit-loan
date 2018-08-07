package com.sixliu.credit.customer;


import lombok.Data;

/**
*@author:MG01867
*@date:2018年8月6日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Data
public class CustomerDTO {

	private String id;
	
	private boolean hitBlacklist;
	
	private String creditlimitId;
}
