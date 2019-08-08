package com.isbing.myInterface;

import java.util.List;

/**
 * Created by song bing
 * Created time 2019/8/8 11:57
 */
public interface SqlSession {
	<T> T selectOne(String statementId, Object param);

	<T> List<T> selectList(String statementId, Object param);
}
