package com.sixliu.credit.account.dto;

import lombok.Data;

/**
 * @author:MG01867
 * @date:2018年7月5日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 确认交易
 */
@Data
public class ConfirmTransactionDTO {

	/** 交易id **/
	private String transactionId;

	/** 确认码 **/
	private String confirmCode;
}
