package com.projectxy.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.projectxy.models.Account;
import com.projectxy.models.UserDao;


@Configuration
@EnableTransactionManagement
public class AppConfiguration {
	@Value("${mysql.datasource.url}")
	public String jdbcMysqlUrl;

	@Value("${mysql.datasource.username}")
	public String datasourceUsername;

	@Value("${mysql.datasource.password}")
	public String datasourcePassword;

	@Value("${hibernate.show_sql}")
	public String hibernateShowSQL;

	@Value("${hibernate.dialect}")
	public String hibernateDialect;

	@Bean(name = "dataSource", destroyMethod = "")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(jdbcMysqlUrl);
		dataSource.setUsername(datasourceUsername);
		dataSource.setPassword(datasourcePassword);
		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", hibernateShowSQL);
		properties.put("hibernate.dialect", hibernateDialect);
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		
		sessionBuilder.addAnnotatedClass(Account.class);
		
		
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDao")
	public UserDao<Account> getUserDao(SessionFactory sessionFactory){
		UserDao<Account> dao = new UserDao<Account>(sessionFactory, Account.class);
		return dao;
	}
}
