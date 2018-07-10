package com.sixliu.credit.common.lock;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author:MG01867
 * @date:2018年7月7日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@Documented
@Target({ METHOD })
@Retention(RUNTIME)
public @interface DistributedWriteLock {

	long tryLockTime() default 0;

	String keyExpression() default "";
}
