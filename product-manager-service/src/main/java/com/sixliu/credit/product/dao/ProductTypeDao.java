package com.sixliu.credit.product.dao;

import java.util.List;

import com.sixliu.credit.product.entity.ProductTypeConfig;

/**
*@author:MG01867
*@date:2018年6月15日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface ProductTypeDao {

	ProductTypeConfig getById(String id);
	
	List<ProductTypeConfig> listAll();
	
	int insert(ProductTypeConfig productType);
}
