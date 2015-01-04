package com.sathyabodh.company.config;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@PropertySource("classpath:application.properties")
//@EnableTransactionManagement
public class JPAConfiguration {
//
//	@Value("${datasource.url}")
//	private String url ;
//	
//	@Value("${datasource.username}")
//	private String username;
//	
//	@Value("${datasource.password}")
//	private String password;
//	
//	@Value("${datasource.driver.name}")
//	private String driver;
//	
//	public DataSource dataSource(){
//		DriverManagerDataSource datasource = new DriverManagerDataSource();
//		datasource.setUrl(url);
//		datasource.setDriverClassName(driver);
//		datasource.setUsername(username);
//		datasource.setPassword(password);
//		return datasource;
//	}
//	
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityMangerFactory(){
//		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
//		bean.setDataSource(dataSource());
//		bean.setPersistenceUnitName("company-persistence");
//		bean.setPackagesToScan("com.sathyabodh.company.domain");
//		bean.setJpaVendorAdapter(jpaVendorAdapter());
//		return bean ;
//	}
//	
//	@Bean
//	public JpaVendorAdapter jpaVendorAdapter(){
//		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//		adapter.setDatabase(Database.H2);
//		adapter.setShowSql(true);
//		return adapter;
//	}
//	
//	@Bean
//	public PlatformTransactionManager transactionManager(){
//		JpaTransactionManager manager = new JpaTransactionManager(entityMangerFactory().getObject());
//		return manager;
//	}
//	
//	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
//		return new PropertyPlaceholderConfigurer();
//	}
}
