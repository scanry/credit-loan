package com.sixliu.credit.common.thread;

/**
 * @author:MG01867
 * @date:2018年7月18日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class HandlerProxyTest implements AsyHandler {

	@Override
	public String getName() {
		return "HandlerProxyTest";
	}
	
	@Override
	public long getCheckInterval() {
		return 6000;
	}

	@Override
	public Object check() {
		return "check";
	}

	@Override
	public void invoke(Object arg) {
		System.out.println(arg);
	}
}