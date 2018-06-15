package com.sixliu.credit.common.exception;

/**
 * @author sixliu E-mail:359852326@qq.com
 * @version 创建时间：2018年6月15日 下午8:29:03 类说明
 */
public abstract class AppException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4457108314644429695L;

	public AppException() {
		super();
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}
	
	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
}
