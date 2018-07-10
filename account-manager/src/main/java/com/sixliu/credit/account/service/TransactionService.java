package com.sixliu.credit.account.service;

import com.sixliu.credit.account.dto.ApplyTakeAmountRequestDTO;
import com.sixliu.credit.account.dto.ApplyTakeAmountResultDTO;
import com.sixliu.credit.account.dto.ConfirmTransactionDTO;
import com.sixliu.credit.account.dto.DepositRequestDTO;
import com.sixliu.credit.account.dto.DepositResultDTO;
import com.sixliu.credit.account.dto.TransferResultDTO;

/**
 * @author:MG01867
 * @date:2018年7月5日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 交易服务
 */
public interface TransactionService {

	/**
	 * 申请取款
	 * 
	 * @param request
	 * @return
	 */
	ApplyTakeAmountResultDTO applyTakeAmount(ApplyTakeAmountRequestDTO request);

	/**
	 * 转账
	 * 
	 * @param request
	 * @return
	 */
	ApplyTakeAmountResultDTO transferAccounts(ApplyTakeAmountRequestDTO request);

	/**
	 * 确认交易
	 * 
	 * @param request
	 * @return
	 */
	String confirmTransaction(ConfirmTransactionDTO drawMoneyConfirm);

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
	TransferResultDTO queryTransferResult(String transferId);
}
