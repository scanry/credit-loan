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
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

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

	private static Map<DistributedWriteLock, GetStampHandler> cache = new HashMap<>();
	private static WeakHashMap<String, DistributedLock> lockCache = new WeakHashMap<>();
	private static ExpressionParser expressionParser = new SpelExpressionParser();

	private interface GetStampHandler {
		String getStamp(Object[] args);
	}

	protected String getStamp(ProceedingJoinPoint joinPoint) {
		MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
		Method method = joinPointObject.getMethod();
		DistributedWriteLock annotation = AnnotationUtils.findAnnotation(method, DistributedWriteLock.class);
		GetStampHandler getStampHandler = cache.get(annotation);
		if (null == getStampHandler) {
			synchronized (cache) {
				getStampHandler = cache.get(annotation);
				if (null == getStampHandler) {
					Object[] args = joinPoint.getArgs();
					if (null != args && args.length == 1&&args[0] instanceof String) {
						getStampHandler = new GetStampHandler() {

							@Override
							public String getStamp(Object[] args) {
								return (String) args[0];
							}
						};
					} else if (!"".equals(annotation.keyExpression())) {
						Expression expression = expressionParser.parseExpression(annotation.keyExpression());
						getStampHandler = new GetStampHandler() {
							@Override
							public String getStamp(Object[] args) {
								return (String) expression.getValue(args);
							}
						};
					}
					if (null == getStampHandler) {
						throw new SystemAppException("the distributedLock is unsupport");
					}
					cache.put(annotation, getStampHandler);
				}
			}
		}
		return getStampHandler.getStamp(joinPoint.getArgs());
	}

	protected DistributedLock getDistributedLock(ProceedingJoinPoint joinPoint) {
		String stamp = getStamp(joinPoint);
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
