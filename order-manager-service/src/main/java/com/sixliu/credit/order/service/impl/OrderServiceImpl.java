package com.sixliu.credit.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixliu.credit.order.CreateCreditOrderDTO;
import com.sixliu.credit.order.dao.OrderDao;
import com.sixliu.credit.order.entity.CreditOrder;
import com.sixliu.credit.order.service.OrderService;
import com.sixliu.credit.product.ProductInnerDTO;
import com.sixliu.credit.product.api.ProductManagerInnerClient;
import com.sixliu.flow.FlowManager;

/**
 * @author:MG01867
 * @date:2018年7月10日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 授信订单服务
 */
@Service
public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao;

	private ProductManagerInnerClient productManagerClient;

	private FlowManager flowManager;

	@Override
	public CreditOrder getOrderById(String orderId) {
		return orderDao.getOrderById(orderId);
	}

	@Override
	public String createOrder(CreateCreditOrderDTO createCreditOrder) {
		ProductInnerDTO product = productManagerClient.get(createCreditOrder.getProductId());
		String flowJobId = flowManager.createFlowJob(product.getCreditApplyFlowModleId(),
				createCreditOrder.getInputUserId(), createCreditOrder.getChannel());
		CreditOrder creditOrder = new CreditOrder();
		creditOrder.setId(createCreditOrder.getId());
		creditOrder.setCustomerId(createCreditOrder.getCustomerId());
		creditOrder.setProductId(createCreditOrder.getProductId());
		creditOrder.setProductSnapshotId(createCreditOrder.getProductSnapshotId());
		creditOrder.setFlowJobId(flowJobId);
		creditOrder.setApplyCreditlimit(createCreditOrder.getApplyCreditlimit());
		creditOrder.setApplyLoanAmount(createCreditOrder.getApplyLoanAmount());
		creditOrder.setApplyLoanTermType(createCreditOrder.getApplyLoanTermType());
		creditOrder.setApplyLoanTerm(createCreditOrder.getApplyLoanTerm());
		creditOrder.setActivityId(createCreditOrder.getActivityId());
		creditOrder.setChannel(createCreditOrder.getChannel());
		creditOrder.setReferenceId(createCreditOrder.getReferenceId());
		creditOrder.setCreateUserId(createCreditOrder.getInputUserId());
		creditOrder.setCreateDate(new Date());
		orderDao.insert(creditOrder);
		return creditOrder.getId();
	}

	@Override
	public List<CreditOrder> listOrderByCustomerId(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancel(String orderId) {

	}

	@Override
	public void delete(String orderId) {

	}
}
