package com.sixliu.credit.product.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.common.dto.ResponseUtils;
import com.sixliu.credit.product.dto.AppliedProduct;
import com.sixliu.credit.product.dto.ProductApplyPrecheckConfig;
import com.sixliu.credit.product.dto.ProductConfigDto;
import com.sixliu.credit.product.dto.ProductConfigQuery;
import com.sixliu.credit.product.entity.ProductConfig;
import com.sixliu.credit.product.service.ProductConfigService;

/**
 * @author:MG01867
 * @date:2018年6月22日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品配置管理controller
 */
@RestController
@RequestMapping("/productConfig")
public class ProductConfigController {

	@Autowired
	private ProductConfigService productConfigService;

	/**
	 * 获取所有可申请的产品
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pageQuery", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<List<AppliedProduct>> pageQuery(ProductConfigQuery productConfigQuery) {
		return ResponseUtils.succeed(productConfigService.listForAllApplied());
	}

	/**
	 * 根据产品id获取产品数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<ProductConfigDto> getById(@NotBlank(message = "the id must not be blank") String id) {
		ProductConfig productConfig = productConfigService.getById(id);
		ProductConfigDto result = null;
		if (null != productConfig) {
			result = new ProductConfigDto();
			BeanUtils.copyProperties(productConfig, result);
		}
		return ResponseUtils.succeed(result);
	}
	
	@RequestMapping(value = "/getByCode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<ProductConfigDto> getByCode(@NotBlank(message = "the code must not be blank") String code) {
		ProductConfig productConfig = productConfigService.getByCode(code);
		ProductConfigDto result = null;
		if (null != productConfig) {
			result = new ProductConfigDto();
			BeanUtils.copyProperties(productConfig, result);
		}
		return ResponseUtils.succeed(result);
	}

	/**
	 * 根据产品id获取产品申请准入配置
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProductApplyPrecheckConfigById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<ProductApplyPrecheckConfig> getProductApplyPrecheckConfigById(
			@NotBlank(message = "the supplierName must not be blank") String id) {
		return null;
	}

}
