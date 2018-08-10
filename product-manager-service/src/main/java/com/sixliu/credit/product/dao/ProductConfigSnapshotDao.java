package com.sixliu.credit.product.dao;

import com.sixliu.credit.product.entity.snapshot.ProductConfigSnapshot;

/**
*@author:MG01867
*@date:2018年6月28日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface ProductConfigSnapshotDao {

	ProductConfigSnapshot get(String id);
	
	int insert(ProductConfigSnapshot productConfigSnapshot);
}
