package com.sornyei.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;


/**
 * Created by gaborsornyei on 16. 02. 20..
 */
@Configuration
@ComponentScan(basePackages = {"com.sornyei"})
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class AppConfig {
	final static Logger logger = Logger.getLogger(AppConfig.class.getName().toUpperCase());

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		logger.info(env.getProperty("jdbc.url"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassname"));
		dataSource.setUsername(env.getProperty("jdbc.userName"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		return dataSource;
	}

}
