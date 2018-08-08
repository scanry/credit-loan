package com.sixliu.credit.quota.api;

import com.sixliu.credit.quota.CreditlimitDTO;

/**
*@author:MG01867
*@date:2018年8月6日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface QuotaManagerClient {

	CreditlimitDTO get(String quotaId);
}
