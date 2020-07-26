package cn.lingnan.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import cn.lingnan.dto.Order;
import cn.lingnan.util.DAO;

public class ClientOrderDAO extends DAO {
	private int i;
	private String sql;
	private Order order = new Order();

//查找全部status为1的
	public Vector<Order> findAllClientOrder() {
		Vector<Order> v = new Vector<>();
		sql = "select * from v_clientorder1";
		super.setP(sql);
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = super.RsHandle();
		for (i = 0; i < list.size(); i++) {
			order = new Order();
			order.setTable_ID(Integer.parseInt(list.get(i).get("Table_Id").toString()));
			order.setMenu_Name(list.get(i).get("Menu_Name").toString());
			order.setMenu_Count(Integer.parseInt(list.get(i).get("Menu_Count").toString()));
			order.setPrice(Integer.parseInt(list.get(i).get("Price").toString()));
			order.setRowPrice(Integer.parseInt(list.get(i).get("RowPrice").toString()));
			order.setOrderStatus(Integer.parseInt(list.get(i).get("OrderStatus").toString()));
			v.add(order);
		}
		return v;
	}

//根据tableid查找全部status为1的
	public Vector<Order> find(int value) {
		Vector<Order> v = new Vector<>();

		sql = "select * from v_clientorder1 where Table_Id=?";
		super.setP(sql);
		try {
			super.getP().setInt(1, value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = super.RsHandle();
		for (i = 0; i < list.size(); i++) {
			order = new Order();
			order.setTable_ID(Integer.parseInt(list.get(i).get("Table_Id").toString()));
			order.setMenu_Name(list.get(i).get("Menu_Name").toString());
			order.setMenu_Count(Integer.parseInt(list.get(i).get("Menu_Count").toString()));
			order.setPrice(Integer.parseInt(list.get(i).get("Price").toString()));
			order.setRowPrice(Integer.parseInt(list.get(i).get("RowPrice").toString()));
			order.setOrderStatus(Integer.parseInt(list.get(i).get("OrderStatus").toString()));
			v.add(order);
		}
		return v;
	}

	public Order find(int value, String MenuName, int orderstatus) {

		sql = "select * from v_clientorder1 where Table_Id=?&&Menu_Name=?&&OrderStatus=?";
		super.setP(sql);
		try {
			super.getP().setInt(1, value);
			super.getP().setString(2, MenuName);
			super.getP().setInt(3, orderstatus);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = super.RsHandle();

		order.setTable_ID(Integer.parseInt(list.get(0).get("Table_Id").toString()));
		order.setMenu_Name(list.get(0).get("Menu_Name").toString());
		order.setMenu_Count(Integer.parseInt(list.get(0).get("Menu_Count").toString()));
		order.setPrice(Integer.parseInt(list.get(0).get("Price").toString()));
		order.setRowPrice(Integer.parseInt(list.get(0).get("RowPrice").toString()));
		order.setOrderStatus(Integer.parseInt(list.get(0).get("OrderStatus").toString()));
		return order;
	}

//查找全部status为2的
	public Vector<Order> findAllClientOrder2() {
		Vector<Order> v = new Vector<>();
		sql = "select * from v_clientorder2";
		super.setP(sql);
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = super.RsHandle();
		for (i = 0; i < list.size(); i++) {
			order = new Order();
			order.setTable_ID(Integer.parseInt(list.get(i).get("Table_Id").toString()));
			order.setMenu_Name(list.get(i).get("Menu_Name").toString());
			order.setMenu_Count(Integer.parseInt(list.get(i).get("Menu_Count").toString()));
			order.setPrice(Integer.parseInt(list.get(i).get("Price").toString()));
			order.setRowPrice(Integer.parseInt(list.get(i).get("RowPrice").toString()));
			order.setOrderStatus(Integer.parseInt(list.get(i).get("OrderStatus").toString()));
			v.add(order);
		}
		return v;
	}

//根据tableid查找全部status为2的
	public Order find2(int value, String MenuName, int orderstatus) {

		sql = "select * from v_clientorder2 where Table_Id=?&&Menu_Name=?&&OrderStatus=?";
		super.setP(sql);
		try {
			super.getP().setInt(1, value);
			super.getP().setString(2, MenuName);
			super.getP().setInt(3, orderstatus);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = super.RsHandle();

		order.setTable_ID(Integer.parseInt(list.get(0).get("Table_Id").toString()));
		order.setMenu_Name(list.get(0).get("Menu_Name").toString());
		order.setMenu_Count(Integer.parseInt(list.get(0).get("Menu_Count").toString()));
		order.setPrice(Integer.parseInt(list.get(0).get("Price").toString()));
		order.setRowPrice(Integer.parseInt(list.get(0).get("RowPrice").toString()));
		order.setOrderStatus(Integer.parseInt(list.get(0).get("OrderStatus").toString()));
		return order;

	}

	public Vector<Order> find2(int value) {
		Vector<Order> v = new Vector<>();

		sql = "select * from v_clientorder2 where Table_Id=?";
		super.setP(sql);
		try {
			super.getP().setInt(1, value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = super.RsHandle();
		for (i = 0; i < list.size(); i++) {
			order = new Order();
			order.setTable_ID(Integer.parseInt(list.get(i).get("Table_Id").toString()));
			order.setMenu_Name(list.get(i).get("Menu_Name").toString());
			order.setMenu_Count(Integer.parseInt(list.get(i).get("Menu_Count").toString()));
			order.setPrice(Integer.parseInt(list.get(i).get("Price").toString()));
			order.setRowPrice(Integer.parseInt(list.get(i).get("RowPrice").toString()));
			order.setOrderStatus(Integer.parseInt(list.get(i).get("OrderStatus").toString()));
			v.add(order);
		}
		return v;
	}

//查找全部status的
	public Vector<Order> findAllClientOrder3() {
		Vector<Order> v = new Vector<>();
		sql = "select * from v_clientorder3";
		super.setP(sql);
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = super.RsHandle();
		for (i = 0; i < list.size(); i++) {
			order = new Order();
			order.setTable_ID(Integer.parseInt(list.get(i).get("Table_Id").toString()));
			order.setMenu_Name(list.get(i).get("Menu_Name").toString());
			order.setMenu_Count(Integer.parseInt(list.get(i).get("Menu_Count").toString()));
			order.setPrice(Integer.parseInt(list.get(i).get("Price").toString()));
			order.setRowPrice(Integer.parseInt(list.get(i).get("RowPrice").toString()));
			order.setOrderStatus(Integer.parseInt(list.get(i).get("OrderStatus").toString()));
			v.add(order);
		}
		return v;
	}

//根据tableid查找全部status的
	public Vector<Order> find3(int value) {
		Vector<Order> v = new Vector<>();

		sql = "select * from v_clientorder3 where Table_Id=?";
		super.setP(sql);
		try {
			super.getP().setInt(1, value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = super.RsHandle();
		for (i = 0; i < list.size(); i++) {
			order = new Order();
			order.setTable_ID(Integer.parseInt(list.get(i).get("Table_Id").toString()));
			order.setMenu_Name(list.get(i).get("Menu_Name").toString());
			order.setMenu_Count(Integer.parseInt(list.get(i).get("Menu_Count").toString()));
			order.setPrice(Integer.parseInt(list.get(i).get("Price").toString()));
			order.setRowPrice(Integer.parseInt(list.get(i).get("RowPrice").toString()));
			order.setOrderStatus(Integer.parseInt(list.get(i).get("OrderStatus").toString()));
			v.add(order);
		}
		return v;
	}

	public Vector<Order> findChefOrder() {
		Vector<Order> v = new Vector<>();
		sql = "select * from v_chef";
		super.setP(sql);
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = super.RsHandle();
		for (i = 0; i < list.size(); i++) {
			order = new Order();
			order.setStaff_Name(list.get(i).get("Staff_Name").toString());
			order.setTable_ID(Integer.parseInt(list.get(i).get("Table_Id").toString()));
			order.setMenu_Name(list.get(i).get("Menu_Name").toString());
			order.setMenu_Count(Integer.parseInt(list.get(i).get("Menu_Count").toString()));
			v.add(order);
		}
		return v;
	}

	public Vector<Order> findChefOrderByName(String Menu_Name) {
		Vector<Order> v = new Vector<>();
		sql = "select * from v_chef where Menu_Name=?";
		super.setP(sql);
		try {
			super.getP().setString(1, Menu_Name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = super.RsHandle();
		for (i = 0; i < list.size(); i++) {
			order = new Order();
			order.setStaff_Name(list.get(i).get("Staff_Name").toString());
			order.setTable_ID(Integer.parseInt(list.get(i).get("Table_Id").toString()));
			order.setMenu_Name(list.get(i).get("Menu_Name").toString());
			order.setMenu_Count(Integer.parseInt(list.get(i).get("Menu_Count").toString()));
			v.add(order);
		}
		return v;

	}

}