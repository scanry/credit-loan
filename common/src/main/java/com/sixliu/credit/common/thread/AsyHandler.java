package com.sixliu.credit.common.thread;

/**
 * @author:MG01867
 * @date:2018年7月18日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 异步handler
 */
public interface AsyHandler {

	/**
	 * handler名称,运行时会将handler名称作为运行线程名字后缀
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 定时执行检查间隔时间(毫秒)
	 * 
	 * @return
	 */
	long getCheckInterval();

	/**
	 * 检查
	 * 
	 * @return
	 */
	Object check();

	/**
	 * 实际执行
	 * 
	 * @param arg
	 */
	void invoke(Object arg);
}
