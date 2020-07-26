package cn.lingnan.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import cn.lingnan.dto.ChefOrder;
import cn.lingnan.util.DAO;

public class ChefOrderDAO extends DAO {

	private String sql;
	private int i;
	private ChefOrder chef=new ChefOrder();
	private boolean flag;
	public boolean AddChefOrder(ChefOrder _chef)
	{
		boolean flag=false;
		chef=_chef;
		sql="insert into ChefOrder values(?,?,?,?)";
		super.setP(sql);
		try {
			super.getP().setInt(1,chef.getStaff_Id());
			super.getP().setInt(2,chef.getTable_Id());
			super.getP().setInt(3, chef.getMenu_Id());
			super.getP().setInt(4, chef.getMenu_count());
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		flag=super.ExUpdate();
		return flag;
	}
	public Vector<ChefOrder> findAllChefOrder()
	{
		Vector<ChefOrder> v=new Vector<>();
		sql="select * from v_cheforder";
		super.setP(sql);
		setRs();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		list=super.RsHandle();
		for(i=0;i<list.size();i++)
		{
			chef=new ChefOrder();
			chef.setStaff_Name(list.get(i).get("Staff_Name").toString());
			chef.setTable_Id(Integer.parseInt(list.get(i).get("Table_Id").toString()));
			chef.setMenu_Name(list.get(i).get("Menu_Name").toString());
			chef.setMenu_count(Integer.parseInt(list.get(i).get("Menu_count").toString()));
			v.add(chef);
		}
		return v;
		
	}
	public Vector<ChefOrder> find(String columnName,String value)
	{
		Vector<ChefOrder> v=new Vector<>();
		sql="select * from v_cheforder where "+columnName+"= ?";
		super.setP(sql);
		
		try {
			super.getP().setString(1, value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		list=super.RsHandle();
		for(i=0;i<list.size();i++)
		{
			chef=new ChefOrder();
			chef.setStaff_Name(list.get(i).get("Staff_Name").toString());
			chef.setTable_Id(Integer.parseInt(list.get(i).get("Table_Id").toString()));
			chef.setMenu_Name(list.get(i).get("Menu_Name").toString());
			chef.setMenu_count(Integer.parseInt(list.get(i).get("Menu_count").toString()));
			v.add(chef);
		}
		return v;
	}
	public Vector<ChefOrder> find(String columnName,int value)
	{
		Vector<ChefOrder> v=new Vector<>();
		sql="select * from v_cheforder where "+columnName+"= ?";
		super.setP(sql);
		
		try {
			super.getP().setInt(1, value);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		list=super.RsHandle();
		for(i=0;i<list.size();i++)
		{
			chef=new ChefOrder();
			chef.setStaff_Name(list.get(i).get("Staff_Name").toString());
			chef.setTable_Id(Integer.parseInt(list.get(i).get("Table_Id").toString()));
			chef.setMenu_Name(list.get(i).get("Menu_Name").toString());
			chef.setMenu_count(Integer.parseInt(list.get(i).get("Menu_count").toString()));
			v.add(chef);
		}
		return v;
	}
	
	public boolean EditChefOrder(ChefOrder _new,ChefOrder _old)
	{
		flag=false;
		sql="update cheforder set Staff_Id=?,Table_Id=?,Menu_Id=?,Menu_count=? where Table_id=? and Menu_Id=?";
		super.setP(sql);
		try {
			super.getP().setInt(1,_new.getStaff_Id());
			super.getP().setInt(2, _new.getTable_Id());
			super.getP().setInt(3, _new.getMenu_Id());
			super.getP().setInt(4, _new.getMenu_count());
			super.getP().setInt(5, _old.getTable_Id());
			super.getP().setInt(6, _old.getMenu_Id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		flag=super.ExUpdate();
		return flag;
		
	}
	public boolean DeleteChefOrder(ChefOrder _chef)
	{
		flag=false;
		sql="delete from cheforder where Table_id=? and Menu_Id=?";
		super.setP(sql);
		try {
			super.getP().setInt(1, _chef.getTable_Id());
			super.getP().setInt(2, _chef.getMenu_Id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		flag=super.ExUpdate();
		return flag;
		
	}

}
