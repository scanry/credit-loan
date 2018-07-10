package com.sixliu.credit.account.dao;

import com.sixliu.credit.account.entity.Account;

/**
 * @author:MG01867
 * @date:2018年7月10日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 账户数据访问dao
 */
public interface AccountDao {

	Account get(String accountId);

	int insert(Account account);

	int updateStatus(String accountId, int newStatus);

	int updateCreditAmount(String accountId, double newCreditAmount);

	int updateFreezeAmount(String accountId, double newFreezeAmount);

	int updateUseAmount(String accountId, double newSurplusAmount, double newAvailableAmount, double newUsedAmount);
}
