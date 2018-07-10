package com.sixliu.credit.account.service;

import java.util.List;

import com.sixliu.credit.account.entity.Account;

/**
*@author:MG01867
*@date:2018年7月5日
*@E-mail:359852326@qq.com
*@version:
*@describe 账户服务
*/
public interface AccountService {

	Account getById(String accountId);
	
	List<Account> listByCustomerId(String customerId);
	
	Account openAccount(String accountId);
	
	void cancelAccount(String accountId);
}
