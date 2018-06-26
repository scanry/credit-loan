package com.sixliu.credit.common.web.interceptor;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.sixliu.credit.common.web.annotation.RetureJson;

/**
*@author:MG01867
*@date:2018年6月26日
*@E-mail:359852326@qq.com
*@version:
*@describe //TODO
*/
public class ResponseHandlerInterceptor implements HandlerMethodReturnValueHandler{

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		returnType.getMethodAnnotation(RetureJson.class);
		return false;
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		mavContainer.setRequestHandled(true);
	}

}
