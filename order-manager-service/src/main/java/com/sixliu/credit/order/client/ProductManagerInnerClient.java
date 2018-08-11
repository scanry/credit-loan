package com.sixliu.credit.order.client;

import javax.validation.constraints.NotBlank;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sixliu.credit.product.ProductInnerDTO;

/**
 * @author:MG01867
 * @date:2018年7月9日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@FeignClient("product-manager")
public interface ProductManagerInnerClient {

	/**
	 * 通过产品id获取产品
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "/product/inner/get", method = RequestMethod.POST)
	ProductInnerDTO get(@NotBlank(message = "The productId must be non blank") @RequestParam(name = "productId") String productId);
	
	/**
	 * 生成产品快照
	 * 
	 * @param productId
	 *            产品id
	 * @return 返回产品快照id
	 */
	@RequestMapping(value = "/product/inner/generateProductSnapshot", method = RequestMethod.POST)
	String generateProductSnapshot(String productId);
}
