package com.sixliu.credit.order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.common.dto.ResponseUtils;
import com.sixliu.credit.order.dto.OrderApplyFormDTO;
import com.sixliu.credit.order.service.OrderService;

/**
*@author:MG01867
*@date:2018年7月7日
*@E-mail:359852326@qq.com
*@version:
*@describe 订单服务http接口适配
*/
@RestController
@RequestMapping("/order")
public class OrderServiceController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/apply", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<String> apply(@RequestBody @Validated OrderApplyFormDTO orderApplyForm) {
		return ResponseUtils.succeed(orderService.apply(orderApplyForm));
	}
}
