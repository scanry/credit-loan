package com.sixliu.credit.common.exception;
/**
*@author:MG01867
*@date:2018年6月22日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public class IllegalOperationAppException extends AppException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4633791705434316940L;

	public IllegalOperationAppException(String message) {
		super(message);
	}

	public IllegalOperationAppException(Throwable cause) {
		super(cause);
	}
	
	public IllegalOperationAppException(String message, Throwable cause) {
		super(message, cause);
	}
}
