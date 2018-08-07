package com.sixliu.credit.core.credit.service;


import com.sixliu.credit.core.credit.CreditApplyDTO;

/**
 * @author:MG01867
 * @date:2018年8月6日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 授信商店服务
 */
public interface CreditShopService {

	/**
	 * 申请授信
	 * 
	 * @param creditApplyFormDTO
	 * @return 返回授信申请id
	 */
	String applyCredit(CreditApplyDTO creditApply);
}
