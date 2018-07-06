package com.sixliu.credit.operate.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sixliu.credit.operate.dao.BaseDao;


/**
 * @author:MG01867
 * @date:2018年2月5日
 * @E-mail:359852326@qq.com
 * @version:
 * @describe mybatis dao接口扫描配置
 */
@Configuration
@AutoConfigureAfter(MybatisBaseConfig.class)
public class MyBatisDaoScannerConfig {

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		String baseMapperPackage = BaseDao.class.getPackage().getName();
		mapperScannerConfigurer.setBasePackage(baseMapperPackage);
		return mapperScannerConfigurer;
	}

}
