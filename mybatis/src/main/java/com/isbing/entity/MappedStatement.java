package com.isbing.entity;

/**
 * Created by song bing
 * Created time 2019/8/8 10:10
 */
public class MappedStatement {
	private String statementId;
	private Class parameterType;
	private Class resultType;
	private String statementType;
	private SqlSource sqlSource;

	public MappedStatement(String statementId, Class parameterType, Class resultType, String statementType,
			SqlSource sqlSource) {
		this.statementId = statementId;
		this.parameterType = parameterType;
		this.resultType = resultType;
		this.statementType = statementType;
		this.sqlSource = sqlSource;
	}

	public MappedStatement() {
	}

	public Class getParameterType() {
		return parameterType;
	}

	public void setParameterType(Class parameterType) {
		this.parameterType = parameterType;
	}

	public Class getResultType() {
		return resultType;
	}

	public void setResultType(Class resultType) {
		this.resultType = resultType;
	}

	public String getStatementType() {
		return statementType;
	}

	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}
}
