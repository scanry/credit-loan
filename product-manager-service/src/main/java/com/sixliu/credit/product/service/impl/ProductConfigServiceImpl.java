package com.sixliu.credit.product.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixliu.credit.common.exception.IllegalArgumentAppException;
import com.sixliu.credit.product.ProductInnerDTO;
import com.sixliu.credit.product.dao.ProductConfigDao;
import com.sixliu.credit.product.dao.ProductConfigSnapshotDao;
import com.sixliu.credit.product.entity.ProductConfig;
import com.sixliu.credit.product.entity.snapshot.ProductConfigSnapshot;
import com.sixliu.credit.product.service.ProductConfigService;

/**
*@author:MG01867
*@date:2018年6月15日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Service
public class ProductConfigServiceImpl implements ProductConfigService{

	final static Logger log = LoggerFactory.getLogger(ProductConfigServiceImpl.class);

	@Autowired
	private ProductConfigDao productConfigDao;
	
	@Autowired
	private ProductConfigSnapshotDao productConfigSnapshotDao;
	
	@Override
	public List<ProductInnerDTO> listForAllApplied() {
		return null;
	}

	@Override
	public ProductConfig getById(String productId) {
		return productConfigDao.get(productId);
	}

	@Override
	public ProductConfig getByCode(String code) {
		return productConfigDao.getByCode(code);
	}

	@Override
	public String generateProductSnapshot(String productId) {
		ProductConfig productConfig=productConfigDao.get(productId);
		if(null==productConfig) {
			throw new IllegalArgumentAppException(String.format("The product[%s] is illegal", productId));
		}
		ProductConfigSnapshot productConfigSnapshot=new ProductConfigSnapshot();
		BeanUtils.copyProperties(productConfig, productConfigSnapshot);
		productConfigSnapshot.setId(null);
		productConfigSnapshot.setOriginalId(productConfig.getId());
		productConfigSnapshotDao.insert(productConfigSnapshot);
		return productConfigSnapshot.getId();
	}

}
