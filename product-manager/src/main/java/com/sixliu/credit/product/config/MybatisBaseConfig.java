package com.sixliu.credit.product.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @author:MG01867
 * @date:2018年2月5日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe
 */
@Configuration
public class MybatisBaseConfig implements TransactionManagementConfigurer {

	private static Logger log = LoggerFactory.getLogger(MybatisBaseConfig.class);

	private final static String typeAliasesPackage = "com.sixliu.product.repository.po";
	private final static String mapperLocation = "classpath*:mybatis/mapper/*.xml";
	private final static String configLocation = "classpath:mybatis/mybatis-config.xml";

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory adminSqlSessionFactoryBean() {
		log.debug("start configruing mybatis");
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setTypeAliasesPackage(typeAliasesPackage);
		SqlSessionFactory sqlSessionFactory = null;
		try {
			// mapperLocationn=classpath*:mybatis/mapper/modules/*.xml
			// 这里必须以classpath*:开头，PathMatchingResourcePatternResolver才会做匹配
			Resource[] mapperResources = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
			bean.setMapperLocations(mapperResources);
			// configLocation=classpath:mybatis/mybatis-config.xml
			Resource configLocationResource = new DefaultResourceLoader().getResource(configLocation);
			bean.setConfigLocation(configLocationResource);
			// bean.setPlugins(new Interceptor[]{pageHelper()});
			sqlSessionFactory = bean.getObject();
		} catch (Exception e) {
			throw new RuntimeException("init mybatis exception", e);
		}
		log.debug("end configruing mybatis");
		return sqlSessionFactory;
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}
