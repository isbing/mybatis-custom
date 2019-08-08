package com.isbing.myInterface;

import com.isbing.entity.Configuration;
import com.isbing.entity.MappedStatement;

import java.util.List;

/**
 * Created by song bing
 * Created time 2019/8/8 14:05
 */
public interface Executor {
	<T> List<T> query(Configuration configuration, MappedStatement mappedStatement, Object param);
}
