package com.sixliu.credit.common.lock;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.curator.framework.CuratorFramework;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import com.sixliu.credit.common.exception.SystemAppException;

/**
 * @author:MG01867
 * @date:2018年7月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 分布式锁aop
 */
public abstract class AbstractDistributedLockAop {

	protected static final Logger log = LoggerFactory.getLogger(AbstractDistributedLockAop.class);

	@Autowired
	private ConfigurableApplicationContext configurableApplicationContext;

	private DistributedLockFactory distributedLockFactory;

	private static Map<Class<? extends GetStampHandler>, GetStampHandler> cache = new HashMap<>();
	private static WeakHashMap<String, DistributedLock> lockCache = new WeakHashMap<>();

	protected GetStampHandler getGetStampHandler(Class<? extends GetStampHandler> clz) {
		GetStampHandler getStampHandler = cache.get(clz);
		if (null == getStampHandler) {
			synchronized (cache) {
				getStampHandler = cache.get(clz);
				if (null == getStampHandler) {
					try {
						getStampHandler = clz.newInstance();
						cache.put(clz, getStampHandler);
					} catch (Exception exception) {
						throw new SystemAppException(exception);
					}
				}
			}
		}
		return getStampHandler;
	}

	protected DistributedLock getDistributedLock(ProceedingJoinPoint joinPoint) {
		MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
		Method method = joinPointObject.getMethod();
		DistributedWriteLockAnnotation annotation = AnnotationUtils.findAnnotation(method,
				DistributedWriteLockAnnotation.class);
		GetStampHandler getStampHandler = getGetStampHandler(annotation.GetStampHandlerClass());
		String stamp = getStampHandler.getStamp(joinPoint.getArgs());
		DistributedLock distributedLock = lockCache.get(stamp);
		if (null == distributedLock) {
			synchronized (lockCache) {
				distributedLock = lockCache.get(stamp);
				if (null == distributedLock) {
					try {
						distributedLock = getDistributedLockFactory().newInstance(stamp);
						lockCache.put(stamp, distributedLock);
					} catch (Exception exception) {
						throw new SystemAppException(exception);
					}
				}
			}
		}
		return distributedLock;
	}

	private DistributedLockFactory getDistributedLockFactory() {
		if (null == distributedLockFactory) {
			synchronized (this) {
				if (null == distributedLockFactory) {
					try {
						distributedLockFactory = configurableApplicationContext.getBean(DistributedLockFactory.class);
					} catch (Exception exception) {
						log.warn(
								"there is not curatorFramework in the spring ApplicationContext and will unsupport default ZkDistributedLockFactory",
								exception);
					}
					if (null == distributedLockFactory) {
						try {
							CuratorFramework curatorFramework = configurableApplicationContext
									.getBean(CuratorFramework.class);
							distributedLockFactory = new ZkDistributedLockFactory(curatorFramework);
						} catch (Exception exception) {
							log.warn(
									"there is not curatorFramework in the spring ApplicationContext and will unsupport default ZkDistributedLockFactory",
									exception);
						}
					}
					if (null == distributedLockFactory) {
						throw new SystemAppException(
								"there is not DistributedLockFactory and will unsupport DistributedLock");
					}
				}
			}
		}
		return distributedLockFactory;
	}

}
