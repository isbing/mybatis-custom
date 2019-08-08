package com.isbing.myIml;

import com.isbing.entity.Configuration;
import com.isbing.myInterface.SqlSession;
import com.isbing.myInterface.SqlSessionFactory;

/**
 * Created by song bing
 * Created time 2019/8/8 12:09
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

	private Configuration configuration;

	public DefaultSqlSessionFactory(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public SqlSession openSession() {
		return new DefaultSqlSession(configuration);
	}

}
