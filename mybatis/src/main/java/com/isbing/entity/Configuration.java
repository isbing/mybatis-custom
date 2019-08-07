package com.isbing.entity;

import javax.sql.DataSource;

/**
 * Created by song bing
 * Created time 2019/8/7 16:21
 */
public class Configuration {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
