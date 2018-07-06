package com.sixliu.credit.account.dto;

import lombok.Data;

/**
*@author:MG01867
*@date:2018年7月5日
*@E-mail:359852326@qq.com
*@version:
*@describe 取款请求
*/
@Data
public class DrawMoneyApplyRequest {

	/**取款账户id**/
	private String accountId;
	/**取款金额**/
	private double amount;
}
