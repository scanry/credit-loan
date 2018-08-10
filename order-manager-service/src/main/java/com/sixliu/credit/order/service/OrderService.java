package com.sixliu.credit.order.service;

import java.util.List;

import com.sixliu.credit.order.CreateCreditOrderDTO;
import com.sixliu.credit.order.entity.CreditOrder;

/**
 * @author:MG01867
 * @date:2018年7月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 订单服务
 */
public interface OrderService {

	/**
	 * 通过订单id查询订单
	 * 
	 * @param orderId
	 *            订单id
	 * @return
	 */
	CreditOrder getOrderById(String orderId);

	/**
	 * 贷款产品订单申请
	 * 
	 * @param CreateCreditOrderDTO
	 *            贷款产品订单申请表单
	 * @return 返回申请成功后的订单id
	 */
	String createOrder(CreateCreditOrderDTO createCreditOrder);

	/**
	 * 通过客户id查询订单列表
	 * 
	 * @param orderId
	 *            订单id
	 * @return
	 */
	List<CreditOrder> listOrderByCustomerId(String customerId);

	/**
	 * 通过订单id取消订单
	 * 
	 * @param orderId
	 */
	void cancel(String orderId);

	/**
	 * 通过订单id删除订单
	 * 
	 * @param orderId
	 */
	void delete(String orderId);
}
