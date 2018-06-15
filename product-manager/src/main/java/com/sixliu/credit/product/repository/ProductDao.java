package com.sixliu.credit.product.repository;

import java.util.List;

import com.sixliu.credit.product.repository.po.Product;

/**
*@author:MG01867
*@date:2018年6月15日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface ProductDao {

	Product getById(Integer id);
	
	List<Product> listByType(Integer typeId);
	
	int insert(Product product);
}
