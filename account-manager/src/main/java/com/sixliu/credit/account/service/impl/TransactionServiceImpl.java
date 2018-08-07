package com.sixliu.credit.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixliu.credit.account.dao.AccountDao;
import com.sixliu.credit.account.dto.ConfirmTransactionDTO;
import com.sixliu.credit.account.dto.DepositRequestDTO;
import com.sixliu.credit.account.dto.DepositResultDTO;
import com.sixliu.credit.account.dto.TakeAmountDTO;
import com.sixliu.credit.account.dto.TakeAmountResultDTO;
import com.sixliu.credit.account.dto.TransactionResultDTO;
import com.sixliu.credit.account.entity.Account;
import com.sixliu.credit.account.service.TransactionService;
import com.sixliu.credit.common.exception.IllegalOperationAppException;

/**
*@author:MG01867
*@date:2018年7月12日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public TakeAmountResultDTO take(TakeAmountDTO request) {
		Account account=accountDao.get(request.getAccountId());
		if(null==account) {
			throw new IllegalOperationAppException("the account[%s] is non-existent");
		}
		return null;
	}

	@Override
	public TransactionResultDTO confirm(ConfirmTransactionDTO drawMoneyConfirm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepositResultDTO deposit(DepositRequestDTO DepositDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionResultDTO queryTransactionResult(String transferId) {
		// TODO Auto-generated method stub
		return null;
	}

}
