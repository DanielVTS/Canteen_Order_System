package cn.lingnan.util;

import java.sql.*;
import java.util.Properties;

public class DatabaseConn {
	private static String driver=null;
	private static String url=null;
	private static String user=null;
	private static String password=null;
	
	public static Connection getConnection() {
		Connection conn=null;
		Properties prop=new Properties();
		prop=new PropertiesLoader("jdbc.properties").getProperties();
		driver=prop.getProperty("driver");
		url=prop.getProperty("url");
		user=prop.getProperty("user");
		password=prop.getProperty("password");
		try {
			Class.forName(driver);
			
			conn=DriverManager.getConnection(url,user,password);
		}
		catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("驱动加载失败");
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("数据库参数错误");
		}
		
		return conn;
		
	}
	
	public static void closeConnection(Connection conn) {
		try {		   
			conn.close();			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Statement stat) {
		try {						
			if(stat!=null) 
				stat.close();
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(PreparedStatement prep) {
		try {						
			if(prep!=null) 
				prep.close();			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(ResultSet rs) {
		try {
			if(rs!=null) 
				rs.close();

			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(ResultSet rs,PreparedStatement prep,Connection conn) {
		closeConnection(rs);
		closeConnection(prep);
		closeConnection(conn);
	}
	
	public static void closeConnection(PreparedStatement prep,Connection conn) {
		closeConnection(prep);
		closeConnection(conn);
	}
			
	public static void closeConnection(ResultSet rs,Statement stat,Connection conn) {		
			closeConnection(rs);
			closeConnection(stat);
			closeConnection(conn);			
	}
	
	public static void closeConnection(Statement stat,Connection conn) {
		closeConnection(stat);
		closeConnection(conn);
	}

}
