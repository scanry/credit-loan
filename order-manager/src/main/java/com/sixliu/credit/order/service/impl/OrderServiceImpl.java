package com.sixliu.credit.order.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.common.exception.IllegalArgumentAppException;
import com.sixliu.credit.common.lock.DistributedWriteLockAnnotation;
import com.sixliu.credit.common.lock.GetStampHandler;
import com.sixliu.credit.order.client.CustomerClient;
import com.sixliu.credit.order.client.ProductClient;
import com.sixliu.credit.order.dto.OrderApplyFormDTO;
import com.sixliu.credit.order.service.OrderService;

/**
 * @author:MG01867
 * @date:2018年7月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@Service
public class OrderServiceImpl implements OrderService {

	protected static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderServiceImpl orderService;

	@Autowired
	private CustomerClient customerClient;
	
	@Autowired
	private ProductClient productClient;

	public static class InnerGetStampHandlerClass implements GetStampHandler {

		@Override
		public String getStamp(Object[] args) {
			OrderApplyFormDTO orderApplyFormDTO = (OrderApplyFormDTO) args[0];
			return orderApplyFormDTO.getCustomerId();
		}
	}

	/**
	 * 前置检查:
	 * 1.存量客户检查。
	 * 2.存量产品检查。
	 * 3.并发申请兼容性检查
	 * 4.已经通过的贷款申请兼容性检查
	 * 5.客户基础额度检查
	 * 6.产品基础额度检查
	 */
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

	@DistributedWriteLockAnnotation(GetStampHandlerClass = InnerGetStampHandlerClass.class)
	protected String doApply(OrderApplyFormDTO orderApplyForm) {
		log.info("申请贷款订单成功");
		return null;
	}

	@Override
	public String getOrderById(String orderId) {
		return null;
	}

	@Override
	public String listOrderByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancel(String orderId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String orderId) {
		// TODO Auto-generated method stub

	}

}
