package com.dao;

import com.domain.User;

public interface UserDao {
	/**
	 * 根据ID查找用户
	 * @param ID 学号
	 * @return 根据用户名找到的用户信息bean,如果没找到返回null
	 */
	public User findUserByID(String ID);
	

}
