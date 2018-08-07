package com.sixliu.credit.core.credit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.core.credit.CreditApplyDTO;
import com.sixliu.credit.core.credit.check.Context;
import com.sixliu.credit.core.credit.check.CreditPreCheckPiping;
import com.sixliu.credit.core.credit.service.CreditShopService;
import com.sixliu.credit.customer.CustomerDTO;
import com.sixliu.credit.order.OrderDTO;
import com.sixliu.credit.order.api.OrderManagerClient;
import com.sixliu.credit.product.ProductDTO;
import com.sixliu.credit.product.api.ProductManagerClient;
import com.sixliu.flow.FlowManager;

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
	private FlowManager flowManager;

	@Autowired
	private ProductManagerClient productManagerClient;

	@Autowired
	private OrderManagerClient orderManagerClient;

	private CreditPreCheckPiping creditPreCheckPiping;
	
	public void init() {
		creditPreCheckPiping=new CreditPreCheckPiping();
	}

	@Override
	public String applyCredit(CreditApplyDTO creditApply) {
		Context context =creditPreCheckPiping.check(creditApply);
		ProductDTO product = context.getProduct();
		CustomerDTO customer = context.getCustomer();
		String productSnapshotId = productManagerClient.generateProductSnapshot(product.getId());
		String flowJobId = flowManager.createFlowJob(product.getCreditApplyFlowModleId(),
				creditApply.getInputUserId(), creditApply.getChannel());
		OrderDTO order = new OrderDTO();
		order.setCustomerId(customer.getId());
		order.setProductId(product.getId());
		order.setProductSnapshotId(productSnapshotId);
		order.setFlowJobId(flowJobId);
		order.setApplyCreditlimit(creditApply.getApplyCreditlimit());
		order.setApplyLoanTermType(creditApply.getApplyLoanTermType());
		order.setApplyLoanTerm(creditApply.getApplyLoanTerm());
		order.setReferenceId(creditApply.getReferenceId());
		order.setChannel(creditApply.getChannel());
		order.setActivityId(creditApply.getActivityId());
		order.setExtendForm(creditApply.getExtendForm());
		order.setInputUserId(creditApply.getInputUserId());
		Response<String> createOrderResult=orderManagerClient.createOrder(order);
		return createOrderResult.getResult();
	}
}
