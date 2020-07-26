package cn.lingnan.DAO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import cn.lingnan.dto.Table;
import cn.lingnan.util.DAO;
public class TableDAO extends DAO {
private int i;

private Table table=new Table();
private boolean flag;
private String sql;
private int rowNum;

public boolean AddTable(Table _table)
{
	flag=false;
	table=_table;
	sql="insert into table_information(Table_Id,Table_Seat,Table_Status,Phone) values(?,?,?,?)";
	super.setP(sql);
	try {
		super.getP().setInt(1, table.getTable_Id());
		super.getP().setInt(2, table.getTable_Seat());
		super.getP().setInt(3, table.getTable_Status());
		super.getP().setString(4, table.getPhone());
		
	} catch (SQLException e) {
		e.printStackTrace();
		flag=false;
	}
	flag=super.ExUpdate();
	
	return flag;
	
}

public boolean EditTable(Table _table)
{
	flag=false;
	table=_table;
	sql="update table_information set Table_seat=?,Table_Status=?,Phone=?,OrderTime=CURRENT_TIMESTAMP where Table_Id=?";
	super.setP(sql);
	try {
		super.getP().setInt(1, table.getTable_Seat());
		super.getP().setInt(2, table.getTable_Status());
		super.getP().setString(3, table.getPhone());
		super.getP().setInt(4, table.getTable_Id());
		
	} catch (SQLException e) {
		e.printStackTrace();
		flag=false;
	}
	flag=super.ExUpdate();
	return flag;
}
public int FindAllColumn()
{
	String table="table_information";
	String column="Table";
	rowNum=super.FindAllColumn(table, column);
	return rowNum;
}
public int FindAllColumn(int ID)
{
	String table="table_information";
	String column="Table";
	rowNum=super.FindAllColumnById(table, column,ID);
	return rowNum;
}

public Vector<Table> FindAllTable()
{
	Vector<Table> v=new Vector<>();
	sql="select * from table_information";
	super.setP(sql);
	setRs();
	List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	list=super.RsHandle();
	for(i=0;i<list.size();i++)
	{
		table=new Table();
		table.setTable_Id(Integer.parseInt(list.get(i).get("Table_Id").toString()));
		table.setTable_Seat(Integer.parseInt(list.get(i).get("Table_Seat").toString()));
		table.setTable_Status(Integer.parseInt(list.get(i).get("Table_Status").toString()));
		table.setPhone(list.get(i).get("Phone").toString());
		table.setOrderTime(list.get(i).get("OrderTime").toString());
		v.add(table);
	}
	return v;
}
public Vector<Table> Find(String ColumnName,int value)
{
	Vector<Table> v=new Vector<>();
	List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
	list=super.find("Table_Information", ColumnName, value);
	for(i=0;i<list.size();i++)
	{
		Table table=new Table();
		table.setTable_Id(Integer.parseInt(list.get(i).get("Table_Id").toString()));
		table.setTable_Seat(Integer.parseInt(list.get(i).get("Table_Seat").toString()));
		table.setTable_Status(Integer.parseInt(list.get(i).get("Table_Status").toString()));
		table.setPhone(list.get(i).get("Phone").toString());
		table.setOrderTime(list.get(i).get("OrderTime").toString());
		v.add(table);
	}
	return v;
}
public Vector<Table> Find(String ColumnName,String value)
{
	Vector<Table> v=new Vector<>();
	List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
	super.find("Table_Information", ColumnName, value);
	for(i=0;i<list.size();i++)
	{
		Table table=new Table();
		table.setTable_Id(Integer.parseInt(list.get(i).get("Table_Id").toString()));
		table.setTable_Seat(Integer.parseInt(list.get(i).get("Table_Seat").toString()));
		table.setTable_Status(Integer.parseInt(list.get(i).get("Table_Status").toString()));
		table.setPhone(list.get(i).get("Phone").toString());
		table.setOrderTime(list.get(i).get("OrderTime").toString());
		v.add(table);
	}
	return v;
}
public boolean DeleteWork(Table _table)
{
	flag=false;
	int ID=_table.getTable_Id();
	String columnName="Table_Id";
	flag=super.DeleteDAO("Table_Information", columnName, ID);
	return flag;
}


public Table FindTable(int id) 
{	
	sql="select * from table_information where Table_Id=?";		
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
	if(!list.isEmpty()) {
		table.setTable_Id(Integer.parseInt(list.get(0).get("Table_Id").toString()));
		table.setTable_Seat(Integer.parseInt(list.get(0).get("Table_Seat").toString()));
		table.setTable_Status(Integer.parseInt(list.get(0).get("Table_Status").toString()));
		table.setPhone(list.get(0).get("Phone").toString());
		table.setOrderTime(list.get(0).get("OrderTime").toString());			
		return table;
	}else return null;
	
}


}
