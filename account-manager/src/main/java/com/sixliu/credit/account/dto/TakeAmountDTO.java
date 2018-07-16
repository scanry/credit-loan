package com.sixliu.credit.account.dto;

import com.sixliu.credit.common.dto.BaseWriteDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
*@author:MG01867
*@date:2018年7月5日
*@E-mail:359852326@qq.com
*@version:
*@describe 申请取款请求
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class TakeAmountDTO extends BaseWriteDTO{

	/**取款账户id**/
	private String accountId;
	/**取款金额**/
	private double amount;
}
