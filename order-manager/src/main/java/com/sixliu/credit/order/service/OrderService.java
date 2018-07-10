package com.sixliu.credit.order.service;

import java.util.List;

import com.sixliu.credit.order.dto.OrderApplyFormDTO;
import com.sixliu.credit.order.entity.Order;

/**
 * @author:MG01867
 * @date:2018年7月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 订单服务
 */
public interface OrderService {

	/**
	 * 贷款产品订单申请
	 * 
	 * @param orderApplyForm
	 *            贷款产品订单申请表单
	 * @return 返回申请成功后的订单id
	 */
	String apply(OrderApplyFormDTO orderApplyForm);
	
	/**
	 * 通过订单id查询订单
	 * @param orderId  订单id
	 * @return 
	 */
	Order getOrderById(String orderId);

	/**
	 * 通过客户id查询订单列表
	 * @param orderId  订单id
	 * @return 
	 */
	List<Order> listOrderByCustomerId(String customerId);

	/**
	 * 通过订单id取消订单
	 * @param orderId
	 */
	void cancel(String orderId);
	
	/**
	 * 通过订单id删除订单
	 * @param orderId
	 */
	void delete(String orderId);
}
