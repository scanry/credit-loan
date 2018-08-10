package com.sixliu.credit.order.dao;

import java.util.List;

import com.sixliu.credit.order.entity.CreditOrder;

/**
*@author:MG01867
*@date:2018年7月10日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface OrderDao {

	int insert(CreditOrder creditOrder);
	
	CreditOrder getOrderById(String orderId);
	
	List<CreditOrder> listOrderByCustomerId(String customerId);
	
	int delById(String orderId);
}
