package com.sixliu.credit.common.exception;
/**
*@author:MG01867
*@date:2018年7月7日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public class SystemAppException extends AppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3956132794110028634L;

	public SystemAppException() {
		super();
	}

	public SystemAppException(String message) {
		super(message);
	}

	public SystemAppException(Throwable cause) {
		super(cause);
	}
	
	public SystemAppException(String message, Throwable cause) {
		super(message, cause);
	}
}
