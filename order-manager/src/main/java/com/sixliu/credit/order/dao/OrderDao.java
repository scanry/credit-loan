package com.sixliu.credit.order.dao;

import java.util.List;

import com.sixliu.credit.order.entity.Order;

/**
*@author:MG01867
*@date:2018年7月10日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface OrderDao {

	Order getOrderById(String orderId);
	
	List<Order> listOrderByCustomerId(String customerId);
	
	int delById(String orderId);
}
