package com.sixliu.credit.product.dao;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sixliu.credit.common.constant.LoanTermType;
import com.sixliu.credit.product.BaseTest;
import com.sixliu.credit.product.CreditApplyMutexType;
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
		productConfig.setCode("JLD");
		productConfig.setName("居乐贷"+System.currentTimeMillis());
		productConfig.setTypeId("1");
		productConfig.setPriority(1);
		productConfig.setCreditApplyMutexType(CreditApplyMutexType.FOR_ALL);
		productConfig.setUseBlacklistGroupId(UUID.randomUUID().toString());
		productConfig.setLoopCreditlimit(true);
		productConfig.setIncreaseCreditlimit(true);
		productConfig.setDecreaseCreditlimit(true);
		productConfig.setLinkCustomerBaseCreditlimit(true);
		productConfig.setCreditApplyMaxMonths(3);
		productConfig.setCreditApplyFlowModleId(UUID.randomUUID().toString());
		productConfig.setCreditApplyRejectInfluenceDays(7);
		productConfig.setMinCreditlimit(10000);
		productConfig.setMaxCreditlimit(100000);
		productConfig.setCreditlimitEffectiveMonths(36);
		productConfig.setMinSingleLoanAmount(500);
		productConfig.setMaxSingleLoanAmount(50000);
		productConfig.setLoanTermType(LoanTermType.MONTH);
		productConfig.setMinSingleLoanTerm(3);
		productConfig.setMaxSingleLoanTerm(36);
		productConfig.setLoanFlowModleId(UUID.randomUUID().toString());
		productConfig.setEffective(false);
		productConfig.setEffectiveDate(new Date());
		productConfig.setExpireDate(new Date());
		productConfig.setRemarks("remarks");
		productConfig.setOwnerId("sixliu");
		productConfig.setCreateUserId("sixliu");
		productConfig.setUpdateUserId("sixliu");
		int result=productDao.insert(productConfig);
		assertTrue(1==result);
	}

	@Test
	public void testGetById() {
		String id="f7d60464-8011-4c54-8ee1-218aac4bf535";
		ProductConfig productConfig=productDao.get(id);
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
		assertTrue(1==result||1!=result);
	}
	
	@Test
	public void testDelByCode() {
		String code="JLD";
		int result=productDao.delByCode(code);
		assertTrue(1==result||1!=result);
	}
}