package com.sixliu.credit.order.config;

import org.springframework.cloud.openfeign.FeignClient;

import com.sixliu.credit.product.api.ProductManagerInnerClient;

/**
 * @author:MG01867
 * @date:2018年7月9日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@FeignClient("product-manager")
public interface ProductManagerInnerClientConfig extends ProductManagerInnerClient{
	
}
