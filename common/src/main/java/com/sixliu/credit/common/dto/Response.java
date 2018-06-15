package com.sixliu.credit.common.dto;
/**
* @author sixliu 
* @E-mail:359852326@qq.com
* @version 创建时间：2018年6月15日 下午8:27:10
* 类说明
*/
public class Response<T>{

	private int code;
	private String message;
	private T result;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
}
