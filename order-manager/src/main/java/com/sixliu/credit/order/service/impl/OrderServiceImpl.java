package com.sixliu.credit.order.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.common.exception.IllegalArgumentAppException;
import com.sixliu.credit.common.lock.DistributedWriteLock;
import com.sixliu.credit.order.client.CustomerClient;
import com.sixliu.credit.order.client.ProductClient;
import com.sixliu.credit.order.dao.OrderDao;
import com.sixliu.credit.order.dto.OrderApplyFormDTO;
import com.sixliu.credit.order.entity.Order;
import com.sixliu.credit.order.service.OrderReviewService;
import com.sixliu.credit.order.service.OrderService;

/**
 * @author:MG01867
 * @date:2018年7月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 订单服务实现
 */
@Service
public class OrderServiceImpl implements OrderService {

	protected static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderServiceImpl orderService;

	@Autowired
	private CustomerClient customerClient;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private OrderReviewService orderReviewService;

	/**
	 * 前置检查: 1.存量客户检查。 2.存量产品检查。 3.并发申请兼容性检查 4.已经通过的贷款申请兼容性检查 5.客户基础额度检查 6.渠道基础额度检查
	 * 7.产品基础额度检查
	 * 
	 * 
	 */
	@DistributedWriteLock(namespace="",keyExpression ="[0].customerId")
	@Override
	public String apply(OrderApplyFormDTO orderApplyForm) {
		Response<String> customerResponse = customerClient.get(orderApplyForm.getCustomerId());
		if (null == customerResponse.getResult()) {
			throw new IllegalArgumentAppException(
					String.format("the customerId[%s] is illegal", orderApplyForm.getCustomerId()));
		}
		Response<String> productResponse = productClient.get(orderApplyForm.getProductId());
		if (null == productResponse.getResult()) {
			throw new IllegalArgumentAppException(
					String.format("the productId[%s] is illegal", orderApplyForm.getProductId()));
		}
		return orderService.doApply(orderApplyForm);
	}
	
	protected String doApply(OrderApplyFormDTO orderApplyForm) {
		return "成功";
	}

	@Override
	public Order getOrderById(String orderId) {
		return orderDao.getOrderById(orderId);
	}

	@Override
	public List<Order> listOrderByCustomerId(String customerId) {
		return orderDao.listOrderByCustomerId(customerId);
	}

	@DistributedWriteLock(namespace="",tryLockTime = 3000)
	@Override
	public void cancel(String orderId) {
		Order order = orderDao.getOrderById(orderId);
		if (null == order) {
			throw new IllegalArgumentAppException(String.format("the order's id[%s] is Illegal", orderId));
		}
		orderReviewService.cancelFlowJob(order.getFlowJobId());
		delete(orderId);
	}
	
	@Override
	public void delete(String orderId) {
		orderDao.delById(orderId);
	}

}
