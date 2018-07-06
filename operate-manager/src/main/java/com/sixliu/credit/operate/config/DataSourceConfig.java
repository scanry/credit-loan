package com.sixliu.credit.operate.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author:MG01867
 * @date:2018年2月5日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe 数据源配置
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig implements EnvironmentAware {

	private static Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

	private Environment environment;

	@Override
	public void setEnvironment(Environment env) {
		this.environment = env;
	}

	@Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
	@Primary
	public DataSource dataSource() {
		log.debug("start configruing dataSource");
		DruidDataSource datasource = new DruidDataSource();
		String jdbcUrl = environment.getProperty("jdbc.url");
		String driverClassName = environment.getProperty("jdbc.driver");
		String username = environment.getProperty("jdbc.username");
		String password = environment.getProperty("jdbc.password");
		int init = environment.getProperty("jdbc.pool.init", Integer.class);
		int minIdle = environment.getProperty("jdbc.pool.minIdle", Integer.class);
		int maxActive = environment.getProperty("jdbc.pool.maxActive", Integer.class);
		datasource.setUrl(jdbcUrl);
		datasource.setDriverClassName(driverClassName);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setInitialSize(init);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		try {
			datasource.setFilters("stat");
		} catch (SQLException e) {
			throw new RuntimeException("init datasource exception", e);
		}
		log.debug("end configruing dataSource");
		return datasource;
	}

}
