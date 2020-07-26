package cn.lingnan.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import cn.lingnan.dto.Work;
import cn.lingnan.util.DAO;

public class ChefWorkDAO extends DAO {
	private boolean flag;
	private Work work=new Work();
	private String sql;
	private int i;
	private int rowNum;
	
	public boolean AddWork(Work _work)
	{
		flag=false;
		work=_work;
		sql="insert into ChefWork values(?,?,?)";
		super.setP(sql);
		try {
		super.getP().setInt(1, work.getID());
		super.getP().setString(2, work.getName());
		super.getP().setString(3, work.getJob());
		flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag=false;
		}
		flag=super.ExUpdate();
				
		return flag;
	}
	public boolean EditWork(Work _work)
	{
		flag=false;
		work=_work;
		sql="update ChefWork set Chef_Job=? where Staff_Id=?";
		super.setP(sql);
		try {
			super.getP().setString(1,work.getJob());
			super.getP().setInt(2, work.getID());
			flag=true;
		} catch (SQLException e) {
			flag=false;
			e.printStackTrace();
		}
		flag=super.ExUpdate();
		return flag;
	}
	public int FindAllColumn()
	{
		String table="ChefWork";
		String column="Staff";
		rowNum=super.FindAllColumn(table, column);
		return rowNum;
	}
	public int FindAllColumn(int ID)
	{
		String table="ChefWork";
		String column="Staff";
		rowNum=super.FindAllColumnById(table, column,ID);
		return rowNum;
	}
	
	
	public Work FindWork(int ID)
	{
		sql="select * from ChefWork where Staff_Id=?";
		super.setP(sql);
		try {
			super.getP().setInt(1, ID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setRs();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list=super.RsHandle();
		work.setID(Integer.parseInt(list.get(0).get("Staff_Id").toString()));
		work.setName(list.get(0).get("Staff_Name").toString());
		work.setJob(list.get(0).get("Chef_Job").toString());
		
		
		return work;
	}
	public Vector<Work> FindAllWork()
	{
		Vector<Work> v=new Vector<>();
		sql="select * from ChefWork";
		super.setP(sql);
		setRs();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list=super.RsHandle();	
		for(i=0;i<list.size();i++)
		{
			Work work=new Work();
			work.setID(Integer.parseInt(list.get(i).get("Staff_Id").toString()));
			work.setName(list.get(i).get("Staff_Name").toString());
			work.setJob(list.get(i).get("Chef_Job").toString());
			v.add(work);		
		}		
		return v;
	}
	public Vector<Work> Find(String ColumnName,String value)
	{
		Vector<Work> v=new Vector<>();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		super.find("ChefWork", ColumnName, value);
		for(i=0;i<list.size();i++)
		{
			Work work=new Work();
			work.setID(Integer.parseInt(list.get(i).get("Staff_Id").toString()));
			work.setName(list.get(i).get("Staff_Name").toString());
			work.setJob(list.get(i).get("Chef_Job").toString());
			v.add(work);
			
		}
		
		
		return v;
	}

public Vector<Work> Find(String ColumnName,int value)
{
	Vector<Work> v=new Vector<>();
	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	super.find("ChefWork", ColumnName, value);
	for(i=0;i<list.size();i++)
	{
		Work work=new Work();
		work.setID(Integer.parseInt(list.get(i).get("Staff_Id").toString()));
		work.setName(list.get(i).get("Staff_Name").toString());
		work.setJob(list.get(i).get("Chef_Job").toString());
		v.add(work);
		
	}
	return v;
	}

	public boolean DeleteWork(Work _work)
	{
		flag=false;
		int ID=_work.getID();
		String columnName="Staff_Id";
		if(!super.IDfind("ChefWork",columnName,ID))
		{
			System.out.println("没有该人");
			return false;
		}
		flag=super.DeleteDAO("ChefWork",columnName, ID);
	return flag;
	}
}