package com.sixliu.credit.order.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sixliu.credit.common.lock.DistributedWriteLockAnnotation;
import com.sixliu.credit.common.lock.GetStampHandler;
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

	public static class InnerGetStampHandlerClass implements GetStampHandler {

		@Override
		public String getStamp(Object[] args) {
			OrderApplyFormDTO orderApplyFormDTO = (OrderApplyFormDTO) args[0];
			return orderApplyFormDTO.getCustomerId();
		}
	}

	@Override
	@DistributedWriteLockAnnotation(GetStampHandlerClass = InnerGetStampHandlerClass.class)
	public String apply(OrderApplyFormDTO orderApplyForm) {
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
