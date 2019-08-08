package com.isbing.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song bing
 * Created time 2019/8/8 14:18
 */
public class BoundSql {
	// jdbc能够执行的SQL
	private String sql;
	// #{id} 中的id取出来
	private List<ParameterMapping> parameterMappingList = new ArrayList<>();

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<ParameterMapping> getParameterMappingList() {
		return parameterMappingList;
	}

	public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
		this.parameterMappingList = parameterMappingList;
	}

	public BoundSql(String sql, List<ParameterMapping> parameterMappingList) {
		this.sql = sql;
		this.parameterMappingList = parameterMappingList;
	}

	public BoundSql() {
	}
}
