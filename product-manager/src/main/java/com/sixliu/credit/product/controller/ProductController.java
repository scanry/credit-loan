package com.sixliu.credit.product.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.common.dto.ResponseUtils;
import com.sixliu.credit.product.dto.ProductDTO;
import com.sixliu.credit.product.dto.ProductQueryDTO;
import com.sixliu.credit.product.service.ProductService;

/**
 * @author:MG01867
 * @date:2018年6月22日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品服务controller
 */
@RestController
@RequestMapping("/productConfig")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 分页查询产品
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<List<ProductDTO>> query(ProductQueryDTO productConfigQuery) {
		productConfigQuery.setEffective(true);
		Date nowDate=new Date();
		productConfigQuery.setEffectiveDate(nowDate);
		productConfigQuery.setExpireDate(nowDate);
		return ResponseUtils.succeed(productService.query(productConfigQuery));
	}
}
