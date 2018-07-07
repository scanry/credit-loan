package com.sixliu.credit.common.lock;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.sixliu.credit.common.exception.SystemAppException;

/**
*@author:MG01867
*@date:2018年7月7日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Aspect
@Component
public class DistributedWriteLockAop extends AbstractDistributedLockAop{

	@Around("@annotation(com.sixliu.credit.common.lock.DistributedWriteLockAnnotation)")
	public void aop(ProceedingJoinPoint joinPoint) {
		DistributedLock distributedLock=null;
		try {
			distributedLock=getDistributedLock(joinPoint);
			distributedLock.writeLock();
			joinPoint.proceed(joinPoint.getArgs());
		} catch (Throwable exception) {
			throw new SystemAppException(exception);
		} finally {
			if(null!=distributedLock) {
				try {
					distributedLock.unwriteLock();
				} catch (Exception exception) {
					throw new SystemAppException(exception);
				}
			}
		}
	}
}
