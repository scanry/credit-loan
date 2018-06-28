package com.sixliu.credit.product.dao;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sixliu.credit.product.BaseTest;
import com.sixliu.credit.product.dao.ProductConfigDao;
import com.sixliu.credit.product.entity.ProductConfig;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class ProductConfigDaoTest extends BaseTest {

	@Autowired
	ProductConfigDao productDao;

	@Test
	public void testInsert() {
		ProductConfig productConfig=new ProductConfig();
		productConfig.setId(UUID.randomUUID().toString());
		productConfig.setCode("JLD");
		productConfig.setName("居乐贷");
		productConfig.setTypeId("1");
		productConfig.setPriority(1);
		productConfig.setApplyMutexForAll(1);
		productConfig.setApplyMutexForSimilar(1);
		productConfig.setApplyMultiple(1);
		productConfig.setLoopQuota(1);
		productConfig.setIncreaseQuota(1);
		productConfig.setDecreaseQuota(1);
		productConfig.setLoanTermType(1);
		productConfig.setLoanTerm(36);
		productConfig.setEffectiveDate(new Date());
		productConfig.setExpireDate(new Date());
		productConfig.setEffective(true);
		productConfig.setApplyDescription("购房信息申请");
		productConfig.setRemarks("remarks");
		productConfig.setOwnerId("sixliu");
		productConfig.setCreateUserId("sixliu");
		productConfig.setUpdateUserId("sixliu");
		int result=productDao.insert(productConfig);
		assertTrue(1==result);
	}

	@Test
	public void testGetById() {
		String id="aec8c173-17d9-40c3-aebc-8f8d2b6d014a";
		ProductConfig productConfig=productDao.getById(id);
		assertTrue(null==productConfig||null!=productConfig);
	}

	@Test
	public void testGetByCode() {
		String code="JLD";
		ProductConfig productConfig=productDao.getByCode(code);
		assertTrue(null==productConfig||null!=productConfig);
	}
	
	@Test
	public void testDelById() {
		String id="aec8c173-17d9-40c3-aebc-8f8d2b6d014a";
		int result=productDao.delById(id);
		assertTrue(1==result);
	}
	
	@Test
	public void testDelByCode() {
		String code="JLD";
		int result=productDao.delByCode(code);
		assertTrue(1==result);
	}
}