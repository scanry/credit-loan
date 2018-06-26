package com.sixliu.credit.common.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
*@author:MG01867
*@date:2018年6月26日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RetureJson {

}
