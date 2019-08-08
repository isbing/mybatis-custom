package com.isbing.dao;

import com.isbing.entity.User;
import com.isbing.myInterface.SqlSession;
import com.isbing.myInterface.SqlSessionFactory;

/**
 * Created by song bing
 * Created time 2019/8/7 14:53
 */
public class UserDaoImpl implements UserDao {

	private SqlSessionFactory sqlSessionFactory;

	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public User queryUserById(User param) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession.selectOne("User.findUserById", param);
	}
}
