package cn.lingnan.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DAO {
	private Connection conn;
	private PreparedStatement p;
	private ResultSet rs;
	private List<Map<String, Object>> list;
	private int rowCount;

    
	private void setConn() {
		this.conn = DatabaseConn.getConnection();
	}
	
	protected Connection getConn() {
		return conn;
	}
	
	protected PreparedStatement setP(String sql) {
		setConn();
		try {
			p=getConn().prepareStatement(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}				
		return p;
		
	}		
	
	protected PreparedStatement getP() {
		return p;
	}	

	protected void setRs() {
		try {
			this.rs = p.executeQuery();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	protected ResultSet getRs() {
		setRs();
		return rs;
	}

	protected boolean ExUpdate() {
		boolean flag=false;
		try {
			p.executeUpdate();
			flag=true;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			DatabaseConn.closeConnection(p, conn);
		}
		return flag;
	}
	protected int FindAllColumn(String table,String column)
	{
		rowCount=0;
		String columnName=column+"_Id";
		String sql="select count("+columnName+") totalCount from "+table;
		setP(sql);
		rs=getRs();
		try {
			while(rs.next())
			{
				rowCount=rs.getInt("totalCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseConn.closeConnection(rs,p, conn);
		}
		
		return rowCount;
	}
	protected int FindAllColumnById(String table,String column,int ID)
	{
		rowCount=0;
		String columnName=column+"_Id";
		String sql="select count("+columnName+") totalCount from "+table+" where "+columnName+ "= ?";
		setP(sql);
		try {
			p.setInt(1, ID);
			rs=getRs();
			while(rs.next())
			{
				rowCount=rs.getInt("totalCount");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DatabaseConn.closeConnection(rs,p, conn);
		}
		
		return rowCount;
		
	}
	
	protected boolean IDfind(String table,int ID) {
		boolean flag=false;
		String columnName=table+"_Id";	
		String sql="select * from "+table+" where "+columnName+" =?";
		setP(sql);		
		try {						
			p.setInt(1, ID);
			rs=getRs();
			while(rs.next()) {			
				flag=true;
				
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			DatabaseConn.closeConnection(rs,p, conn);
		}
		return flag;
	}
	
	protected boolean IDfind(String table,String columName,int ID) {
		boolean flag=false;
//		String columnName=table+"_Id";	
		
		String sql="select * from "+table+" where "+columName+" =?";
		setP(sql);		
		try {						
			p.setInt(1, ID);
			rs=getRs();
			while(rs.next()) {			
				flag=true;
				
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			DatabaseConn.closeConnection(rs,p, conn);
		}
		return flag;
	}
	
	
	protected List<Map<String, Object>> find(String table,String ColumnName,String value) {	
		String sql="select * from "+table+" where "+ColumnName+" =?";
		setP(sql);					
		try {
			p.setString(1, value);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		setRs();
		return RsHandle();
	}
	
	protected List<Map<String, Object>> find(String table,String ColumnName,int value) {	
		String sql="select * from "+table+" where "+ColumnName+" =?";
		setP(sql);					
		try {
			p.setInt(1, value);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		setRs();
		return RsHandle();
	}
	
	public List<Map<String, Object>> RsHandle() {
		list = new ArrayList<Map<String, Object>>();
		ResultSetMetaData md;
		try {
			md = rs.getMetaData();
			int columnCount = md.getColumnCount(); // 获得列数
			while (rs.next()) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(rowData);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} // 获得结果集结构信息,元数据
		finally {
			DatabaseConn.closeConnection(rs, p, conn);
		}
		return list;
	}
	
	public boolean DeleteDAO(String table,int ID) {
		boolean flag=false;
		String columnName=table+"_Id";
		String sql="delete from "+table+" where "+columnName+" =?";		
		setP(sql);
		try {			
			getP().setInt(1, ID);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		flag=ExUpdate();
		return flag;
	}
	
	public boolean DeleteDAO(String table,String columnName,int ID) {
		  boolean flag=false;
		  String sql="delete from "+table+" where "+columnName+" =?";  
		  setP(sql);
		  try {   
		   getP().setInt(1, ID);
		  } catch (SQLException e) {
		   // TODO 自动生成的 catch 块
		   e.printStackTrace();
		  }
		  flag=ExUpdate();
		  return flag;
		 }
	
	public boolean RSnull(){
		try {
			return rs.next();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	
}
