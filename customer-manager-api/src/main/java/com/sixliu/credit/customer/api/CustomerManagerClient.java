package com.sixliu.credit.customer.api;

import com.sixliu.credit.customer.CustomerDTO;

/**
 * @author:MG01867
 * @date:2018年8月6日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */

public interface CustomerManagerClient {
	
	CustomerDTO getAndHitBlacklist(String customerId,String blacklistGroup);
	
}
