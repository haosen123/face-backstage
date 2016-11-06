package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.domain.User;
import com.util.JDBCUtils;

public class MySqlUserDao2 implements UserDao{

	public User findUserByID(String ID) {
		String sql = "select * from users where ID=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, ID);
			rs = ps.executeQuery();
			if(rs.next()){
				User user = new User();
				user.setPhotopath(rs.getString("photopath"));
				user.setName(rs.getString("name"));
				user.setDepartment(rs.getString("dep"));
				user.setDepartment(rs.getString("department"));
				user.setPosition(rs.getString("position"));
				return user;
			}else{
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JDBCUtils.close(rs, ps, conn);
		}
	}

}
