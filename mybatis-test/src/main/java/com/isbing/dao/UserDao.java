package com.isbing.dao;

import com.isbing.entity.User;

/**
 * Created by song bing
 * Created time 2019/8/7 14:52
 */
public interface UserDao {
	/**
	 * 根据用户Id查询用户信息
	 * @param param
	 * @return
	 */
	User queryUserById(User param);
}
