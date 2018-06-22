package com.sixliu.credit.product.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.common.dto.ResponseUtils;
import com.sixliu.credit.product.dto.AppliedProduct;
import com.sixliu.credit.product.dto.ProductApplyPrecheckConfig;
import com.sixliu.credit.product.service.ProductManager;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品管理controller
 */
@RestController
@RequestMapping("/productManager")
public class ProductManagerController {

	@Autowired
	private ProductManager productManager;

	/**
	 * 获取所有可申请的产品
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listForApplied", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<List<AppliedProduct>> listForAllApplied() {
		return ResponseUtils.succeed(productManager.listForAllApplied());
	}

	/**
	 * 根据产品id获取产品数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public AppliedProduct getById(@NotBlank(message = "the supplierName must not be blank") String id) {
		return null;
	}
	
	/**
	 * 根据产品id获取产品申请准入配置
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProductApplyPrecheckConfigById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<ProductApplyPrecheckConfig> getProductApplyPrecheckConfigById(@NotBlank(message = "the supplierName must not be blank") String id) {
		return null;
	}
}
