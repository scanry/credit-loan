package com.sixliu.credit.account.service;

import com.sixliu.credit.account.dto.TakeAmountDTO;
import com.sixliu.credit.account.dto.TakeAmountResultDTO;
import com.sixliu.credit.account.dto.ConfirmTransactionDTO;
import com.sixliu.credit.account.dto.DepositRequestDTO;
import com.sixliu.credit.account.dto.DepositResultDTO;
import com.sixliu.credit.account.dto.TransactionResultDTO;

/**
 * @author:MG01867
 * @date:2018年7月5日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 交易服务
 */
public interface TransactionService {

	/**
	 * 取款
	 * @param request
	 * @return
	 */
	TakeAmountResultDTO take(TakeAmountDTO request);

	/**
	 * 确认交易
	 * 
	 * @param request
	 * @return
	 */
	TransactionResultDTO confirm(ConfirmTransactionDTO drawMoneyConfirm);

	/**
	 * 存款
	 * 
	 * @param DepositDTO
	 * @return
	 */
	DepositResultDTO deposit(DepositRequestDTO DepositDTO);

	/**
	 * 查询交易结果
	 * 
	 * @param requestId
	 * @return
	 */
	TransactionResultDTO queryTransactionResult(String transferId);
}
