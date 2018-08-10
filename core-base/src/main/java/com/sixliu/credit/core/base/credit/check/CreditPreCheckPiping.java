package com.sixliu.credit.core.base.credit.check;

import com.sixliu.credit.core.base.credit.CreditApplyDTO;

/**
*@author:MG01867
*@date:2018年8月7日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public class CreditPreCheckPiping {

	public Context check(CreditApplyDTO creditApplyDTO) {
		Context context = new Context();
		context.setCreditApply(creditApplyDTO);
		return context;
	}
}
