package com.sixliu.credit.product.service;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sixliu.credit.product.BaseTest;
import com.sixliu.credit.product.ProductInnerDTO;

/**
*@author:MG01867
*@date:2018年6月28日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public class ProductConfigServiceTest extends BaseTest {

	@Autowired
	ProductConfigService productConfigService;

	@Test
	public void testInsert() {
		List<ProductInnerDTO> list=productConfigService.listForAllApplied();
		log.info("", list);
		assertFalse(false);
	}

}
