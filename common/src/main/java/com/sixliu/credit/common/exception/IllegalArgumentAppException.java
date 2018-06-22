package com.sixliu.credit.common.exception;
/**
*@author:MG01867
*@date:2018年6月22日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public class IllegalArgumentAppException extends AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8918058940492068933L;


	public IllegalArgumentAppException(String message) {
		super(message);
	}

	public IllegalArgumentAppException(String message, Throwable cause) {
		super(message, cause);
	}
}