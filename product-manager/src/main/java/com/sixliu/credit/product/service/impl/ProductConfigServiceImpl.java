package com.sixliu.credit.product.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixliu.credit.product.dao.ProductConfigDao;
import com.sixliu.credit.product.dto.AppliedProduct;
import com.sixliu.credit.product.entity.ProductConfig;
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
	
	@Override
	public List<AppliedProduct> listForAllApplied() {
		return null;
	}

	@Override
	public ProductConfig getById(String id) {
		return productConfigDao.getById(id);
	}

	@Override
	public ProductConfig getByCode(String code) {
		return productConfigDao.getByCode(code);
	}

}
