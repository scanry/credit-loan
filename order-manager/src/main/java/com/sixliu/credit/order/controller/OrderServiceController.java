package com.sixliu.credit.order.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.common.dto.ResponseUtils;
import com.sixliu.credit.order.OrderDTO;
import com.sixliu.credit.order.OrderMutexDTO;
import com.sixliu.credit.order.api.OrderManagerClient;
import com.sixliu.credit.order.dto.CreditApplyFormDTO;
import com.sixliu.credit.order.entity.Order;
import com.sixliu.credit.order.service.OrderService;

/**
 * @author:MG01867
 * @date:2018年7月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 订单服务http接口适配
 */
@RestController
@RequestMapping("/order")
public class OrderServiceController implements OrderManagerClient {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/cancel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<Void> cancel(@RequestBody @Validated CreditApplyFormDTO orderApplyForm) {
		orderService.cancel(orderApplyForm.getCustomerId());
		return ResponseUtils.succeed();
	}

	@RequestMapping(value = "/getOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@Override
	public Response<OrderDTO> getOrder(@RequestParam(required = true) String orderId) {
		Order order = orderService.getOrderById(orderId);
		OrderDTO result = null;
		if (null != order) {
			result = new OrderDTO();
			BeanUtils.copyProperties(order, result);
		}
		return ResponseUtils.succeed(result);
	}

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@Override
	public Response<String> createOrder(@RequestBody @Validated OrderDTO order) {
		return ResponseUtils.succeed(orderService.createOrder(order));
	}

	@Override
	public Response<Boolean> hasMutexOrder(OrderMutexDTO orderMutex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<Boolean> cancelOrder(String customerId, String orderId) {
		// TODO Auto-generated method stub
		return null;
	}
}
