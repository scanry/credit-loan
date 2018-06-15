package com.sixliu.credit.product.restful;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.common.dto.ResponseUtils;
import com.sixliu.credit.product.restful.dto.AppliedProduct;
import com.sixliu.credit.product.service.ProductManager;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品管理restful接口
 */
@RestController
public class ProductManagerRestful {

	@Autowired
	private ProductManager productManager;

	/**
	 * 获取所有可申请的产品
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productManager/listForApplied", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Response<List<AppliedProduct>> listForAllApplied() {
		return ResponseUtils.succeed(productManager.listForAllApplied());
	}

	/**
	 * 根据产品id获取产品数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/productManager/getById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public AppliedProduct getById(@NotBlank(message = "the supplierName must not be blank") String id) {
		return null;
	}
}
