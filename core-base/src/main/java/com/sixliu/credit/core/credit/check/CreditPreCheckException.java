package com.sixliu.credit.core.credit.check;

import com.sixliu.credit.common.exception.AppException;

/**
*@author:MG01867
*@date:2018年8月7日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public class CreditPreCheckException extends AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8101366403843488887L;

	public CreditPreCheckException(String message) {
		super(message);
	}

	public CreditPreCheckException(String message, Throwable cause) {
		super(message, cause);
	}
}
