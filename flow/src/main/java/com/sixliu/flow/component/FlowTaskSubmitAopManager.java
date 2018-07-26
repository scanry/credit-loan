package com.sixliu.flow.component;

import java.util.Map;

/**
*@author:MG01867
*@date:2018年7月26日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public class FlowTaskSubmitAopManager {

	/** 异步handler缓存 **/
	private Map<String, FlowTaskSubmitAop> cache;
	
	public FlowTaskSubmitAop get(String clzName) {
		return cache.get(clzName);
	}
}
