package com.sixliu.credit.core.credit.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sixliu.credit.core.credit.CreditApplyDTO;
import com.sixliu.credit.core.credit.check.Context;
import com.sixliu.credit.core.credit.check.CreditPreCheck;
import com.sixliu.credit.core.credit.check.CreditPreCheckException;
import com.sixliu.credit.product.ProductDTO;
import com.sixliu.credit.product.api.ProductManagerClient;
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
public class CreditPreCheckForProduct implements CreditPreCheck {

	@Autowired
	private ProductManagerClient productManagerClient;

	@Autowired
	private QuotaManagerClient quotaManagerClient;

	@Override
	public void check(Context context) {
		CreditApplyDTO creditApplyDTO=context.getCreditApply();
		ProductDTO productDTO = productManagerClient.get(creditApplyDTO.getProductId());
		if (null == productDTO) {
			throw new CreditPreCheckException(
					String.format("The product[%s] is non-existent", creditApplyDTO.getProductId()));
		}
		if (!productDTO.getEffective()) {
			throw new CreditPreCheckException(
					String.format("The product[%s] is not effective", creditApplyDTO.getProductId()));
		}
		context.setProduct(productDTO);
		CreditlimitDTO prductCreditlimit = quotaManagerClient.get(productDTO.getCreditlimitId());
		if (null == prductCreditlimit) {
			throw new CreditPreCheckException(
					String.format("The product[%s] is Illegal admittance", productDTO.getCreditlimitId()));
		}
		if (!prductCreditlimit.getEffective()) {
			throw new CreditPreCheckException(String.format("The creditlimit[%s] of product[%s] is not effective",
					productDTO.getCreditlimitId(), productDTO.getId()));
		}
		if (creditApplyDTO.getApplyCreditlimit() > prductCreditlimit.getSurplusAmount()) {
			throw new CreditPreCheckException(String.format("The creditlimit[%s] of product[%s] is not enough",
					productDTO.getCreditlimitId(), productDTO.getId()));
		}
		context.setProductCreditlimit(prductCreditlimit);
	}

}
