package com.sixliu.credit.operate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author:MG01867
 * @date:2018年6月15日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe //TODO
 */
@SpringBootApplication
public class StartUp implements WebMvcConfigurer {

	final static Logger log = LoggerFactory.getLogger(StartUp.class);

	static {
		/** 设置jetty maxFormContentSize 默认为10M **/
		System.setProperty("org.eclipse.jetty.server.Request.maxFormContentSize", String.valueOf(1024 * 1024 * 10));
	}

	public static void main(String[] args) {
		SpringApplication.run(StartUp.class);
	}

	/**
	 * 拦截器配置
	 */
	public void addInterceptors(InterceptorRegistry registry) {

	}
}