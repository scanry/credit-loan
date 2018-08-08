package com.sixliu.credit.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixliu.credit.order.OrderDTO;
import com.sixliu.credit.order.dao.OrderDao;
import com.sixliu.credit.order.entity.Order;
import com.sixliu.credit.order.service.OrderService;

/**
*@author:MG01867
*@date:2018年7月10日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	@Override
	public Order getOrderById(String orderId) {
		return orderDao.getOrderById(orderId);
	}

	@Override
	public String createOrder(OrderDTO order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> listOrderByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancel(String orderId) {
		
	}

	@Override
	public void delete(String orderId) {
		
	}
}
