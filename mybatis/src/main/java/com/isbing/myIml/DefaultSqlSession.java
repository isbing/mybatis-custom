package com.isbing.myIml;

import com.isbing.entity.Configuration;
import com.isbing.myInterface.Executor;
import com.isbing.entity.MappedStatement;
import com.isbing.myInterface.SqlSession;

import java.util.List;

/**
 * Created by song bing
 * Created time 2019/8/8 12:05
 */
public class DefaultSqlSession implements SqlSession {

	private Configuration configuration;

	public DefaultSqlSession(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public <T> T selectOne(String statementId, Object param) {
		List<T> resultList = this.selectList(statementId, param);
		if (resultList != null && resultList.size() == 1) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public <T> List<T> selectList(String statementId, Object param) {
		// 通过statementId 获取 MappedStatement对象
		MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
		if (mappedStatement != null) {
			// 执行查询的流程。。需要交给 Executor来处理
			Executor executor = new SimpleExecutor();
			return executor.query(configuration, mappedStatement, param);
		}
		return null;
	}
}
