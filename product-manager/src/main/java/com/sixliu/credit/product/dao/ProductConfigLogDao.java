package com.sixliu.credit.product.dao;

import java.util.List;

import com.sixliu.credit.product.entity.ProductConfig;
import com.sixliu.credit.product.entity.ProductConfigLog;

/**
*@author:MG01867
*@date:2018年6月28日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface ProductConfigLogDao {


	ProductConfigLog getById(String id);
	
	List<ProductConfig> listByCode(String code);
	
	List<ProductConfig> listByOriginalId(String typeId);
	
	int insert(ProductConfigLog productConfigLog);
	
}
