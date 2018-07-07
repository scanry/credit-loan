package com.sixliu.credit.common.lock;
/**
*@author:MG01867
*@date:2018年7月7日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public interface DistributedLockFactory {

	DistributedLock newInstance(String stamp);
}
