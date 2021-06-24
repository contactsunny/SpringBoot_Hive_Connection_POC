package com.contactsunny.poc.springboothiveconnectionpoc.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.hadoop.security.UserGroupInformation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class HiveConfig {

	@Value("${hive.connectionURL}")
	private String hiveConnectionURL;

	@Value("${hive.username}")
	private String userName;

	@Value("${hive.password}")
	private String password;

//	@Value("${hive.kerberos.keyTabLocation}")
//	private String keyTabLocation;
//
//	@Value("${hive.kerberos.krb5Location}")
//	private String krb5Location;
//
//	@Value("${hive.kerberos.principle}")
//	private String principle;

	public DataSource getHiveDataSource() throws IOException {

//		System.setProperty("javax.security.auth.useSubjectCredsOnly", "true");
//		System.setProperty("java.security.krb5.conf", this.krb5Location);
//		org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
//		conf.set("hadoop.security.authentication", "kerberos");
//		UserGroupInformation.setConfiguration(conf);
//		UserGroupInformation.loginUserFromKeytab(this.principle, this.keyTabLocation);
		
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(this.hiveConnectionURL);
		dataSource.setDriverClassName("org.apache.hive.jdbc.HiveDriver");
		dataSource.setUsername(this.userName);
		dataSource.setPassword(this.password);

		return dataSource;
	}
	
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate getJDBCTemplate() throws IOException {
		return new JdbcTemplate(getHiveDataSource());
	}
}

