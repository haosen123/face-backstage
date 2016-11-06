package com.service;

import com.dao.UserDao;
import com.domain.User;
import com.exception.MsgException;
import com.factory.DaoFactory;

public class UserService {
//	private XmlUserDao dao = new XmlUserDao();
//	private MySqlUserDao dao = new MySqlUserDao();
	private UserDao dao = (UserDao) DaoFactory.getFactory().getDao();

	public User findUserByID(String ID) throws MsgException{
		//1.检查ID是否存在,如果没有则提示
		if(dao.findUserByID(ID)==null)
		{throw new MsgException("不存在此ID");}
		//2.如果存在则调用dao中的方法获取用户信息
		else return dao.findUserByID(ID);
	}
	
}
