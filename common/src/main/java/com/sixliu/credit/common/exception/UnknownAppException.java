package com.sixliu.credit.common.exception;
/**
*@author:MG01867
*@date:2018年6月22日
*@E-mail:359852326@qq.com
*@version:
*@describe 未知的应用异常
*/
public class UnknownAppException extends AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2701514020644020771L;

	public UnknownAppException() {
		super();
	}

	public UnknownAppException(String message) {
		super(message);
	}

	public UnknownAppException(Throwable cause) {
		super(cause);
	}
	
	public UnknownAppException(String message, Throwable cause) {
		super(message, cause);
	}
}
