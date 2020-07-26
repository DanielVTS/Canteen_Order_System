package cn.lingnan.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import cn.lingnan.dto.Staff;
import cn.lingnan.util.DAO;

public class StaffDAO extends DAO {

	private boolean flag;
	private Staff staff=new Staff();
	private String sql;
	private int rowNum;
	
	
	public boolean addStaff(Staff _staff)
	{
		flag=false;
		staff=_staff;
		sql="insert into Staff values(?,?,?,?,?)";
		super.setP(sql);
		
		try {
			super.getP().setInt(1,staff.getID());
			super.getP().setString(2,staff.getName());
			super.getP().setString(3,staff.getJob());
			super.getP().setString(4,staff.getGender());
			super.getP().setString(5,staff.getPassword());

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		flag=super.ExUpdate();
		return flag;	
	}
	
	public boolean editStaff(Staff _satff)
	{
		flag=false;
		staff=_satff;
		sql="update Staff set Staff_Id=?,Staff_Name=?,Job=?,Gender=?,StaffPassword=? where Staff_id=?";
		super.setP(sql);
		try {
		super.getP().setInt(1, staff.getID());
		super.getP().setString(2,staff.getName());
		super.getP().setString(3,staff.getJob());
		super.getP().setString(4,staff.getGender());
		super.getP().setString(5,staff.getPassword());
		super.getP().setInt(6,staff.getID());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		flag=super.ExUpdate();
		return flag;
		
		
	}
	public boolean FindStaffExist(int id)
	{
		flag=super.IDfind("Staff",id);
		return flag;
	}
	public Staff FindStaff(int id)
	{
		sql="select * from Staff where staff_Id=?";
		super.setP(sql);
		try {
			super.getP().setInt(1, id);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list=super.RsHandle();
		
		
		staff.setID(Integer.parseInt(list.get(0).get("Staff_Id").toString()));
		staff.setName(list.get(0).get("Staff_Name").toString());
		staff.setJob(list.get(0).get("Job").toString());
		staff.setGender(list.get(0).get("Gender").toString());
		staff.setPassword(list.get(0).get("StaffPassword").toString());	
		return staff;
		
	}
	public int FindAllColumn()
	{
		String table="Staff";
		String column="Staff";
		rowNum=super.FindAllColumn(table,column);
		return rowNum;
	}
	
	public int FindAllColumn(int ID)
	{
		String table="Staff";
		String column="Staff";
		rowNum=super.FindAllColumnById(table, column, ID);
		return rowNum;
	}
	public Vector<Staff> findAllStaff()
	{
		Vector<Staff> v=new Vector<>();
		sql="select * from Staff";
		super.setP(sql);
		setRs();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		list=super.RsHandle();
		for(int i=0;i<list.size();i++)
		{
		staff=new Staff();
		staff.setID(Integer.parseInt(list.get(i).get("Staff_Id").toString()));
		staff.setName(list.get(i).get("Staff_Name").toString());
		staff.setJob(list.get(i).get("Job").toString());
		staff.setGender(list.get(i).get("Gender").toString());
		staff.setPassword(list.get(i).get("StaffPassword").toString());
		v.add(staff);
	}
		return v;
	}
	public Vector<Staff> find(String ColumnName,int value)
	{
		Vector<Staff> v=new Vector<>();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		list=super.find("Staff", ColumnName, value);
		for(int i=0;i<list.size();i++)
		{
			staff=new Staff();
			staff.setID(Integer.parseInt(list.get(i).get("Staff_Id").toString()));
			staff.setName(list.get(i).get("Staff_Name").toString());
			staff.setJob(list.get(i).get("Job").toString());
			staff.setGender(list.get(i).get("Gender").toString());
			staff.setPassword(list.get(i).get("StaffPassword").toString());
			v.add(staff);
		}
		return v;
		
	}
	public Vector<Staff> find(String ColumnName,String value)
	{
		Vector<Staff> v=new Vector<>();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		list=super.find("Staff", ColumnName, value);
		for(int i=0;i<list.size();i++)
		{
			staff=new Staff();
			staff.setID(Integer.parseInt(list.get(0).get("Staff_Id").toString()));
			staff.setName(list.get(0).get("Staff_Name").toString());
			staff.setJob(list.get(0).get("Job").toString());
			staff.setGender(list.get(0).get("Gender").toString());
			staff.setPassword(list.get(0).get("StaffPassword").toString());
			v.add(staff);
		}
		return v;
		
	}
	public boolean DeleteStaff(Staff _Staff)
	{
		flag=false;
		int ID=_Staff.getID();
		if(!super.IDfind("Staff", ID));
		{
		flag=false;
		System.out.println("没有该人");
		}
		flag=super.DeleteDAO("Staff", ID);
		return flag;
		
	}
}
