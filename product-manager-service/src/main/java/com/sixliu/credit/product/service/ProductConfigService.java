package com.sixliu.credit.product.service;

import java.util.List;

import com.sixliu.credit.product.dto.ProductDTO;
import com.sixliu.credit.product.entity.ProductConfig;

/**
*@author:MG01867
*@date:2018年6月15日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface ProductConfigService {

	ProductConfig getById(String id);
	
	ProductConfig getByCode(String code);
	
	List<ProductDTO> listForAllApplied();
}
