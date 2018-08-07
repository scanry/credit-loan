package com.sixliu.credit.order.component;

import com.sixliu.credit.order.dto.CreditApplyFormDTO;

/**
*@author:MG01867
*@date:2018年7月9日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@FunctionalInterface
public interface OrderApplyInterceptor {

	void preHandle(CreditApplyFormDTO orderApplyForm);
}
