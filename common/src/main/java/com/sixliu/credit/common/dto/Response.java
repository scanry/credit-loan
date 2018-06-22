package com.sixliu.credit.common.dto;

import lombok.Data;

/**
* @author sixliu 
* @E-mail:359852326@qq.com
* @version 创建时间：2018年6月15日 下午8:27:10
* 类说明
*/
@Data
public class Response<T>{

	private int code;
	private String message;
	private T result;
}
