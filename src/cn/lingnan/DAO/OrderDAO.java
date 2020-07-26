package cn.lingnan.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import cn.lingnan.dto.Order;
import cn.lingnan.util.DAO;

public class OrderDAO extends DAO {
	private Order order;
	private boolean flag;
	private String sql;
	private MenuDAO md=new MenuDAO();
	
	public boolean AddOrder(Order _order){
		flag=false;		
		order=_order;
		String sql="insert into clientorder set Table_Id=?,Menu_Id=?,Menu_Count=?,Price=?,OrderStatus=?";
		super.setP(sql);
		
		try {
			super.getP().setInt(1, order.getTable_ID());
			super.getP().setInt(2, order.getMenu_ID());
			super.getP().setInt(3, order.getMenu_Count());
			int price= md.FindPrice(order.getMenu_ID());
			super.getP().setInt(4, price);
			super.getP().setInt(5, order.getOrderStatus());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}			 
		flag=super.ExUpdate();
		return flag;
		
	}	
	
	public boolean DeleteOrder(Order _order) {
		flag=false;
		
		if(!findOrder(_order.getTable_ID(), _order.getOrderStatus())) {
			System.out.println("该订单不存在！");
			return false;
		}
		String sql="delete from clientorder where Table_Id=?&&Menu_Id=?&&OrderStatus=?";		
		super.setP(sql);
		try {
			super.getP().setInt(1, _order.getTable_ID());
			int MenuId=md.FindMenuId(_order.getMenu_Name());
			super.getP().setInt(2, MenuId);
			super.getP().setInt(3, _order.getOrderStatus());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		flag=super.ExUpdate();
		return flag;
	}
	
	
	public Vector<Order> findAllOrder(){
		Vector<Order> v=new Vector<>();
		sql="select * from Order";		
		super.setP(sql);			
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list=super.RsHandle();		
		for(int i=0;i<list.size();i++) {
			order=new Order();
			
			order.setMenu_ID(Integer.parseInt(list.get(i).get("Menu_Id").toString()));	
			order.setTable_ID(Integer.parseInt(list.get(i).get("Table_Id").toString()));			
			order.setMenu_Count(Integer.parseInt(list.get(i).get("Menu_Count").toString()));
			order.setOrderStatus(Integer.parseInt(list.get(i).get("OrderStatus").toString()));
			v.add(order);
		}
		return v;
	}
	
	public Vector<Order> find(int Table_Id,int Status){
		Vector<Order> v=new Vector<>();
		String sql="select * from clientorder where Table_Id =? and OrderStatus=?";
		super.setP(sql);					
		try {
			super.getP().setInt(1, Table_Id);
			super.getP().setInt(2, Status);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list=super.RsHandle();		
		for(int i=0;i<list.size();i++) {
			order=new Order();
			order.setMenu_ID(Integer.parseInt(list.get(i).get("Menu_Id").toString()));		
			order.setTable_ID(Integer.parseInt(list.get(i).get("Table_Id").toString()));			
			order.setMenu_Count(Integer.parseInt(list.get(i).get("Menu_Count").toString()));
			order.setOrderStatus(Integer.parseInt(list.get(i).get("OrderStatus").toString()));
			v.add(order);
		}
		return v;
		
	}

	public int CalculatePrice(int Table_Id) {
		int price=0;
		String sql="call resturantsystem.P_OrderPrice(?, ?)";
		
		 
		try {
			CallableStatement call=super.getConn().prepareCall(sql);
			call.setInt(1,Table_Id);
			call.registerOutParameter(2,Types.INTEGER);
			price=call.getInt(2);
			System.out.println(call.getInt(2)); 
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return price;
	}
	
	

	public boolean EditOrder(Order _new,Order _old) {
		flag=false;
		sql="update clientorder set Table_Id=?,Menu_Id=?,Price=?,Menu_Count=?,OrderStatus=? where Table_Id=?&&Menu_Id=?&&OrderStatus=?";
		super.setP(sql);
		try {
			super.getP().setInt(1, _new.getTable_ID());
			int MenuId=md.FindMenuId(_new.getMenu_Name());
			super.getP().setInt(2, MenuId);
			int price= md.FindPrice(_new.getMenu_Name());
			super.getP().setInt(3, price);
			super.getP().setInt(4, _new.getMenu_Count());
			super.getP().setInt(5, _new.getOrderStatus());
			
			super.getP().setInt(6, _old.getTable_ID());
			int oldId=md.FindMenuId(_old.getMenu_Name());
			super.getP().setInt(7, oldId);
			super.getP().setInt(8, _old.getOrderStatus());
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		flag=super.ExUpdate();
		return flag;		
	}
	
		

	public boolean findOrder(int Table_ID,int status) {
		order=new Order();
		order.setTable_ID(Table_ID);
		order.setOrderStatus(status);
		String sql="select * from clientorder where Table_Id =? and OrderStatus=?";
		super.setP(sql);					
		try {
			super.getP().setInt(1, Table_ID);
			super.getP().setInt(2, status);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		setRs();		
		return super.RSnull();
	}
	
	
}
