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
		//1.���ID�Ƿ����,���û������ʾ
		if(dao.findUserByID(ID)==null)
		{throw new MsgException("�����ڴ�ID");}
		//2.������������dao�еķ�����ȡ�û���Ϣ
		else return dao.findUserByID(ID);
	}
	
}
