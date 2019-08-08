package com.isbing.entity;

import com.isbing.utils.GenericTokenParser;
import com.isbing.utils.ParameterMappingTokenHandler;

/**
 * Created by song bing
 * Created time 2019/8/8 10:44
 */
public class SqlSource {
	private String sqlText;

	public SqlSource(String sqlText) {
		this.sqlText = sqlText;
	}

	public SqlSource() {
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	public BoundSql getBoundSql() {
		ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
		GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", handler);
		String sql = tokenParser.parse(this.sqlText);
		return new BoundSql(sql, handler.getParameterMappings());
	}
}
