package com.sixliu.credit.core.credit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.core.base.credit.CreditApplyDTO;
import com.sixliu.credit.core.base.credit.check.Context;
import com.sixliu.credit.core.base.credit.check.CreditPreCheckPiping;
import com.sixliu.credit.core.base.credit.component.CreditOrderIdGenerator;
import com.sixliu.credit.core.credit.service.CreditShopService;
import com.sixliu.credit.customer.CustomerDTO;
import com.sixliu.credit.order.CreateCreditOrderDTO;
import com.sixliu.credit.order.api.OrderManagerClient;
import com.sixliu.credit.product.ProductDTO;
import com.sixliu.credit.product.api.ProductManagerClient;

/**
 * @author:MG01867
 * @date:2018年8月6日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@Service
public class CreditShopServiceImpl implements CreditShopService {

	@Autowired
	private ProductManagerClient productManagerClient;

	@Autowired
	private OrderManagerClient orderManagerClient;

	private CreditPreCheckPiping creditPreCheckPiping;
	
	private CreditOrderIdGenerator creditOrderIdGenerator;
	
	public void init() {
		creditPreCheckPiping=new CreditPreCheckPiping();
	}

	@Override
	public String applyCredit(CreditApplyDTO creditApply) {
		Context context =creditPreCheckPiping.check(creditApply);
		ProductDTO product = context.getProduct();
		CustomerDTO customer = context.getCustomer();
		String productSnapshotId = productManagerClient.generateProductSnapshot(product.getId());
		String id=creditOrderIdGenerator.generator(product.getId());
		CreateCreditOrderDTO createCreditOrder = new CreateCreditOrderDTO();
		createCreditOrder.setActivityId(id);
		createCreditOrder.setCustomerId(customer.getId());
		createCreditOrder.setProductId(product.getId());
		createCreditOrder.setProductSnapshotId(productSnapshotId);
		createCreditOrder.setApplyCreditlimit(creditApply.getApplyCreditlimit());
		createCreditOrder.setApplyLoanTermType(creditApply.getApplyLoanTermType());
		createCreditOrder.setApplyLoanTerm(creditApply.getApplyLoanTerm());
		createCreditOrder.setReferenceId(creditApply.getReferenceId());
		createCreditOrder.setChannel(creditApply.getChannel());
		createCreditOrder.setActivityId(creditApply.getActivityId());
		createCreditOrder.setExtendForm(creditApply.getExtendForm());
		createCreditOrder.setInputUserId(creditApply.getInputUserId());
		Response<String> createOrderResult=orderManagerClient.createOrder(createCreditOrder);
		return createOrderResult.getResult();
	}
}
