package com.sixliu.credit.order.api;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.order.OrderDTO;
import com.sixliu.credit.order.OrderMutexDTO;

/**
*@author:MG01867
*@date:2018年8月7日
*@E-mail:359852326@qq.com
*@version:
*@describe 订单管理客户端
*/
public interface OrderManagerClient {

	/**
	 * 创建授信申请订单
	 * @param order
	 * @return	返回订单id
	 */
	Response<OrderDTO> getOrder(String orderId);
	
	/**
	 * 创建授信申请订单
	 * @param order
	 * @return	返回订单id
	 */
	Response<String> createOrder(OrderDTO order);
	
	/**
	 * 是否有互斥的订单
	 * @param orderMutex
	 * @return
	 */
	Response<Boolean> hasMutexOrder(OrderMutexDTO orderMutex);
	
	/**
	 * 创建授信申请订单
	 * @param order
	 * @return	返回订单id
	 */
	Response<Boolean> cancelOrder(String customerId, String orderId);
}
