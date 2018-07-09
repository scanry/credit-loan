package com.sixliu.credit.order.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sixliu.credit.common.dto.Response;

/**
*@author:MG01867
*@date:2018年7月9日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface ProductClient {

	@RequestMapping(value = "/customer/invoke", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<String> get(String productId);
}
