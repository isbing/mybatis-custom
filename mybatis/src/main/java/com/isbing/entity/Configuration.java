package com.isbing.entity;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by song bing
 * Created time 2019/8/7 16:21
 */
public class Configuration {
	private DataSource dataSource;
	private Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

	public Map<String, MappedStatement> getMappedStatementMap() {
		return mappedStatementMap;
	}

	public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
		this.mappedStatementMap.put(statementId, mappedStatement);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
