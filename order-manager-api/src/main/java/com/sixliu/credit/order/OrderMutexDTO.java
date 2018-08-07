package com.sixliu.credit.order;

import lombok.Data;

/**
*@author:MG01867
*@date:2018年8月7日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Data
public class OrderMutexDTO {

	private String customerId;
	
	private String productTypeId;
	
	private String productId;
}
