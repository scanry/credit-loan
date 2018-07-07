package com.sixliu.credit.common.lock;
/**
*@author:MG01867
*@date:2018年7月7日
*@E-mail:359852326@qq.com
*@version:
*@describe 分布式锁
*/
public interface DistributedLock {

	void readLock()throws Exception;
	
	void unreadlock()throws Exception;
	
	void writeLock()throws Exception;
	
	void unwriteLock()throws Exception;
}
