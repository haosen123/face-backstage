package com.dao;

import com.domain.User;

public interface UserDao {
	/**
	 * ����ID�����û�
	 * @param ID ѧ��
	 * @return �����û����ҵ����û���Ϣbean,���û�ҵ�����null
	 */
	public User findUserByID(String ID);
	

}
