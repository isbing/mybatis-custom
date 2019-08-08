package com.isbing.entity;

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
}
