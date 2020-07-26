package cn.lingnan.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import cn.lingnan.dto.ChefOrder;
import cn.lingnan.util.JDBCUtil;

public class ChefOrderImpl {


	public List<ChefOrder> query() {
		List<ChefOrder> cheforders = new ArrayList<ChefOrder>();
		
		Connection conn = JDBCUtil.getConn();
		
		String SQL = "select * from cheforder";
		 
		PreparedStatement preparedStatement = null;
		ResultSet resultSet=null;
		try {
			preparedStatement = conn.prepareStatement(SQL);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				ChefOrder cheforder = new ChefOrder();
				cheforder.setStaff_Id(resultSet.getInt("Staff_Id"));
				cheforder.setMenu_Id(resultSet.getInt("Menu_Id"));
				cheforder.setTable_Id(resultSet.getInt("Table_Id"));
				cheforder.setMenu_count(resultSet.getInt("Menu_count"));
				cheforders.add(cheforder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(resultSet, preparedStatement, conn);
		}
		return cheforders;
	}
//	public static void main(String[] args) {
//		ChefOrderImpl c = new ChefOrderImpl();
//		System.out.println();		
//	}
	
	
	public List<ChefOrder> findChefOrderById(int Menu_Id) {
		List<ChefOrder> cheforders = new ArrayList<ChefOrder>();
		Connection conn = JDBCUtil.getConn();
		ResultSet resultSet=null;
		PreparedStatement preparedStatement = null;		
		String SQL = "select * from cheforder where Menu_Id = ?";
		
		try {
			preparedStatement = conn.prepareStatement(SQL);
			preparedStatement.setInt(1,Menu_Id);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				ChefOrder cheforder = new ChefOrder();
				cheforder.setStaff_Id(resultSet.getInt("Staff_Id"));
				cheforder.setMenu_Id(resultSet.getInt("Menu_Id"));
				cheforder.setTable_Id(resultSet.getInt("Table_Id"));
				cheforder.setMenu_count(resultSet.getInt("Menu_count"));				
				cheforders.add(cheforder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(resultSet, preparedStatement, conn);
		}
		return  cheforders;
	}
	
	
	
	public static int deleteById(int Menu_Id) {
		Connection conn = JDBCUtil.getConn();
		String SQl = "delete from cheforder where Menu_Id=?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(SQl);
			preparedStatement.setInt(1,Menu_Id);
			return  preparedStatement.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			JDBCUtil.close(null,preparedStatement, conn);
		}
		return 0;
	}
	
	public static int update(ChefOrder cheforder) {
	Connection conn = JDBCUtil.getConn();	
	String SQL="update cheforder set Menu_count=? and Table_Id=?  and Staff_Id=? where Menu_Id=?";	
	PreparedStatement preparedStatement = null;
	//ResultSet resultSet=null;
	try {
		preparedStatement = conn.prepareStatement(SQL);
//		preparedStatement.setInt(1,Menu_Id);		
//		preparedStatement.executeUpdate();
		//ChefOrder cheforder = new ChefOrder();
		preparedStatement.setInt(1,cheforder.getMenu_count());
		preparedStatement.setInt(2,cheforder.getTable_Id());
		preparedStatement.setInt(3,cheforder.getStaff_Id());
		preparedStatement.setInt(4,cheforder.getTable_Id());
		preparedStatement.setInt(5,cheforder.getMenu_Id());
		return preparedStatement.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		JDBCUtil.close(null, preparedStatement, conn);
	}
			return 0;	
	}
	
	
	
	
}
