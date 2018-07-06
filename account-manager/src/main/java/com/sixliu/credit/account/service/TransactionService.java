package com.sixliu.credit.account.service;

import com.sixliu.credit.account.dto.DrawMoneyApplyRequest;
import com.sixliu.credit.account.dto.DrawMoneyApplyResult;
import com.sixliu.credit.account.dto.DrawMoneyConfirm;
import com.sixliu.credit.account.dto.DrawMoneyResult;

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
	 * @param request
	 * @return
	 */
	DrawMoneyApplyResult applyDrawMoney(DrawMoneyApplyRequest request);
	
	
	/**
	 * 确认取款
	 * @param request
	 * @return
	 */
	void confirmDrawMoney(DrawMoneyConfirm drawMoneyConfirm);
	
	/**
	 * 查询取款结果
	 * @param requestId
	 * @return
	 */
	DrawMoneyResult queryDrawMoney(String requestId);
	

	/**
	 * 还款
	 * @param accountId
	 * @param amount
	 */
	void repayment(String accountId,double amount);
}
