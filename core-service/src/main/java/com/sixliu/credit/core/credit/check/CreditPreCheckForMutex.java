package com.sixliu.credit.core.credit.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sixliu.credit.common.dto.Response;
import com.sixliu.credit.core.credit.check.Context;
import com.sixliu.credit.core.credit.check.CreditPreCheck;
import com.sixliu.credit.core.credit.check.CreditPreCheckException;
import com.sixliu.credit.customer.CustomerDTO;
import com.sixliu.credit.order.OrderMutexDTO;
import com.sixliu.credit.order.api.OrderManagerClient;
import com.sixliu.credit.product.CreditApplyMutexType;
import com.sixliu.credit.product.ProductDTO;

/**
 * @author:MG01867
 * @date:2018年8月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 产品授信申请互斥检查
 */
@Component
public class CreditPreCheckForMutex implements CreditPreCheck {

	@Autowired
	private OrderManagerClient orderManagerClient;

	@Override
	public void check(Context context) {
		ProductDTO product = context.getProduct();
		CustomerDTO customer = context.getCustomer();
		boolean pass = true;
		if (CreditApplyMutexType.NONE != product.getCreditApplyMutexType()) {
			OrderMutexDTO orderMutex = new OrderMutexDTO();
			orderMutex.setCustomerId(customer.getId());
			orderMutex.setProductTypeId(product.getTypeId());
			orderMutex.setProductId(product.getId());
			Response<Boolean> result = orderManagerClient.hasMutexOrder(orderMutex);
			pass=result.getResult();
		}
		if (!pass) {
			throw new CreditPreCheckException(String.format("The customer[%s] has already credit order of product[%s]",
					customer.getId(), product.getId()));
		}
	}

}
