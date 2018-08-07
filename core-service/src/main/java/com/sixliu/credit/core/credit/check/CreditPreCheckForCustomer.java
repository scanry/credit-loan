package com.sixliu.credit.core.credit.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sixliu.credit.core.credit.CreditApplyDTO;
import com.sixliu.credit.core.credit.check.Context;
import com.sixliu.credit.core.credit.check.CreditPreCheck;
import com.sixliu.credit.core.credit.check.CreditPreCheckException;
import com.sixliu.credit.customer.CustomerDTO;
import com.sixliu.credit.customer.api.CustomerManagerClient;
import com.sixliu.credit.product.ProductDTO;
import com.sixliu.credit.quota.CreditlimitDTO;
import com.sixliu.credit.quota.api.QuotaManagerClient;

/**
 * @author:MG01867
 * @date:2018年8月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@Component
public class CreditPreCheckForCustomer implements CreditPreCheck {

	@Autowired
	private CustomerManagerClient customerManagerClient;

	@Autowired
	private QuotaManagerClient quotaManagerClient;

	@Override
	public void check(Context context) {
		CreditApplyDTO creditApplyDTO = context.getCreditApply();
		ProductDTO product = context.getProduct();
		CustomerDTO customer = customerManagerClient.getAndHitBlacklist(creditApplyDTO.getCustomerId(),
				product.getBlacklistGroupId());
		if (null == customer) {
			throw new CreditPreCheckException(
					String.format("The customer[%s] is non-existent", creditApplyDTO.getCustomerId()));
		}
		if (customer.isHitBlacklist()) {
			throw new CreditPreCheckException(String.format("The customer[%s] is hit blacklist[%s]",
					customer.getId(), product.getBlacklistGroupId()));
		}
		context.setCustomer(customer);
		if(1==product.getLinkCustomerBaseQuota()) {
			CreditlimitDTO customerCreditlimit = quotaManagerClient.get(customer.getCreditlimitId());
			if (null == customerCreditlimit) {
				throw new CreditPreCheckException(
						String.format("The customer[%s] is Illegal registered", customer.getId()));
			}
			if (!customerCreditlimit.getEffective()) {
				throw new CreditPreCheckException(String.format("The creditlimit[%s] of customer[%s] is not effective",
						customer.getCreditlimitId(), customer.getId()));
			}
			context.setCustomerCreditlimit(customerCreditlimit);
		}
	}

}
