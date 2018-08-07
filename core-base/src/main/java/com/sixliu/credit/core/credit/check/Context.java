package com.sixliu.credit.core.credit.check;

import com.sixliu.credit.core.credit.CreditApplyDTO;
import com.sixliu.credit.customer.CustomerDTO;
import com.sixliu.credit.product.ProductDTO;
import com.sixliu.credit.quota.CreditlimitDTO;

import lombok.Data;

/**
 * @author:MG01867
 * @date:2018年8月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@Data
public class Context {

	private CreditApplyDTO creditApply;
	
	private ProductDTO product;
	
	private CreditlimitDTO productCreditlimit;
	
	private CustomerDTO customer;
	
	private CreditlimitDTO customerCreditlimit;
}
