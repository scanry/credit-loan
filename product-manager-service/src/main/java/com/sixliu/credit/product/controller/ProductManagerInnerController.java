package com.sixliu.credit.product.controller;

import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sixliu.credit.product.ProductInnerDTO;
import com.sixliu.credit.product.api.ProductManagerInnerClient;
import com.sixliu.credit.product.entity.ProductConfig;
import com.sixliu.credit.product.service.ProductConfigService;

/**
 * @author:MG01867
 * @date:2018年7月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品查询 GraphQL Controller
 */
@RestController
@RequestMapping("/product/inner")
public class ProductManagerInnerController implements ProductManagerInnerClient {

	@Autowired
	private ProductConfigService productService;

	@RequestMapping(value = "/get/{productId}", method = RequestMethod.GET)
	@Override
	public ProductInnerDTO get(
			@NotBlank(message = "The productId must be non blank") @PathParam(value = "productId") String productId) {
		ProductConfig productConfig = productService.getById(productId);
		ProductInnerDTO productInnerDTO = null;
		if (null != productConfig) {
			productInnerDTO = new ProductInnerDTO();
			BeanUtils.copyProperties(productConfig, productInnerDTO);
		}
		return productInnerDTO;
	}

	@RequestMapping(value = "/generateProductSnapshot/{productId}", method = RequestMethod.GET)
	@Override
	public String generateProductSnapshot(
			@NotBlank(message = "The productId must be non blank") @PathParam(value = "productId") String productId) {
		return productService.generateProductSnapshot(productId);
	}

}
