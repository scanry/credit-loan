package com.sixliu.credit.product.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author:MG01867
 * @date:2018年2月9日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe
 */
@Configuration
public class AppConfig implements EnvironmentAware {

	private Environment environment;

	private String appName;

	@Override
	public void setEnvironment(Environment env) {
		this.environment = env;
		this.appName = env.getProperty("spring.application.name");
		if (StringUtils.isBlank(appName)) {
			throw new IllegalArgumentException("please set name of application first");
		}
	}

	public String getAppName() {
		return appName;
	}

	public String getStr(String key) {
		return environment.getProperty(key);
	}

	public String getStrRequired(String key) {
		String value = environment.getProperty(key);
		if (StringUtils.isBlank(value)) {
			throw new IllegalArgumentException(String.format("the config[%s] is required", key));
		}
		return value;
	}

	public String getStr(String key, String defaultValue) {
		return environment.getProperty(key, defaultValue);
	}

	public int getInt(String key) {
		return environment.getProperty(key, Integer.class);
	}

	public int getInt(String key, int defaultValue) {
		return environment.getProperty(key, Integer.class, defaultValue);
	}

	public <T> T get(String key, Class<T> cla) {
		return environment.getProperty(key, cla);
	}

	public <T> T get(String key, Class<T> cla, T defaultValue) {
		return environment.getProperty(key, cla, defaultValue);
	}
}
