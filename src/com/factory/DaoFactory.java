package com.factory;

import java.io.FileReader;
import java.util.Properties;

import com.util.JDBCUtils;

public class DaoFactory {
	private static DaoFactory factory = new DaoFactory();
	private static Properties prop = null;
	static{
		try{
			prop = new Properties();
			prop.load(new FileReader(DaoFactory.class.getClassLoader().getResource("config.properties").getPath()));
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	private DaoFactory() {
	}
	
	public static DaoFactory getFactory(){
		return factory;
	}
	
	public JDBCUtils getDao(){
		try{
			String clazz = prop.getProperty("UserDao");
			return  (JDBCUtils) Class.forName(clazz).newInstance();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
