package cn.lingnan.DAO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import cn.lingnan.dto.Menu;
import cn.lingnan.util.DAO;

public class MenuDAO extends DAO {
	
	private boolean flag;
	private Menu menu=new Menu();
	private String sql;
	private int rowNum;
	public boolean AddMenu(Menu _menu) {
		flag=false;
		menu=_menu;		
		sql="insert into menu(Menu_Id,Menu_Name,Menu_Kind,Menu_Status,Price) values(?,?,?,?,?)";
		super.setP(sql);
		try {
			super.getP().setInt(1, menu.getID());
			super.getP().setString(2, menu.getName());
			super.getP().setString(3, menu.getKind());
			super.getP().setInt(4, menu.getStatus());
			super.getP().setInt(5, menu.getPrice());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		flag=super.ExUpdate();
		return flag;
	}

	public boolean EditMenu(Menu _menu) {
		flag=false;
		menu=_menu;
		sql="update menu set Menu_Name=?,Menu_Kind=?,Menu_Status=?,Price=? where Menu_Id=?";
		super.setP(sql);
		try {
			
//			super.getP().setInt(1, menu.getID());
			super.getP().setString(1, menu.getName());
			super.getP().setString(2, menu.getKind());
			super.getP().setInt(3, menu.getStatus());
			super.getP().setInt(4, menu.getPrice());
			super.getP().setInt(5, menu.getID());
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		flag=super.ExUpdate();
		return flag;		
	}
	
	public boolean FindMenuExist(int ID) {
		flag=super.IDfind("Menu",ID);
		return flag;		
	}
	public int FindAllColumn()
	{
		String table="Menu";
		String column="Menu";
		rowNum=super.FindAllColumn(table, column);
		return rowNum;
	}
	public int FindAllColumn(int ID)
	{
		String table="Menu";
		String column="Menu";
		rowNum=super.FindAllColumnById(table, column, ID);
		return rowNum;
	}
	

	public int FindPrice(int ID)
	{
			Menu menu=this.FindMenu(ID);
			return menu.getPrice();
}
	public int FindPrice(String MenuName)
	{
		Menu menu=this.FindMenu(MenuName);
		return menu.getPrice();
	}
	
	public int FindMenuId(String MenuName)
	{
		Menu menu=this.FindMenu(MenuName);
		return menu.getID();
	}


	

	public Menu FindMenu(String MenuName) {			
		sql="select * from menu where Menu_Name=?";		
		super.setP(sql);	
		try {
			super.getP().setString(1, MenuName);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list=super.RsHandle();						
		menu.setID(Integer.parseInt(list.get(0).get("Menu_Id").toString()));		
		menu.setName(list.get(0).get("Menu_Name").toString());			
		menu.setKind(list.get(0).get("Menu_Kind").toString());
		menu.setStatus(Integer.parseInt(list.get(0).get("Menu_Status").toString()));
		menu.setPrice(Integer.parseInt(list.get(0).get("Price").toString()));			
		return menu;
	}
	public Menu FindMenu(int ID) {			
		sql="select * from menu where menu_Id=?";		
		super.setP(sql);	
		try {
			super.getP().setInt(1, ID);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list=super.RsHandle();						
		menu.setID(Integer.parseInt(list.get(0).get("Menu_Id").toString()));		
		menu.setName(list.get(0).get("Menu_Name").toString());			
		menu.setKind(list.get(0).get("Menu_Kind").toString());
		menu.setStatus(Integer.parseInt(list.get(0).get("Menu_Status").toString()));
		menu.setPrice(Integer.parseInt(list.get(0).get("Price").toString()));			
		return menu;
	}
	
	public Vector<Menu> findAllMenu(){
		Vector<Menu> v=new Vector<>();
		sql="select * from menu";		
		super.setP(sql);			
		setRs();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list=super.RsHandle();		
		for(int i=0;i<list.size();i++) {
			menu=new Menu();
			menu.setID(Integer.parseInt(list.get(i).get("Menu_Id").toString()));		
			menu.setName(list.get(i).get("Menu_Name").toString());			
			//rs.getString("Menu_Name");
			menu.setKind(list.get(i).get("Menu_Kind").toString());
			menu.setStatus(Integer.parseInt(list.get(i).get("Menu_Status").toString()));
			menu.setPrice(Integer.parseInt(list.get(i).get("Price").toString()));
			v.add(menu);
		}
		return v;
	}
	
	public Vector<Menu> find(String ColumnName,String value){
		Vector<Menu> v=new Vector<>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list=super.find("Menu", ColumnName, value);
		for(int i=0;i<list.size();i++) {
			menu=new Menu();
			menu.setID(Integer.parseInt(list.get(i).get("Menu_Id").toString()));		
			menu.setName(list.get(i).get("Menu_Name").toString());			
			//rs.getString("Menu_Name");
			menu.setKind(list.get(i).get("Menu_Kind").toString());
			menu.setStatus(Integer.parseInt(list.get(i).get("Menu_Status").toString()));
			menu.setPrice(Integer.parseInt(list.get(i).get("Price").toString()));
			v.add(menu);
		}
		return v;
		
	}
	
	public Vector<Menu> find(String ColumnName,int value){
		Vector<Menu> v=new Vector<>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list=super.find("Menu", ColumnName, value);
		for(int i=0;i<list.size();i++) {
			menu=new Menu();
			menu.setID(Integer.parseInt(list.get(i).get("Menu_Id").toString()));		
			menu.setName(list.get(i).get("Menu_Name").toString());			
			//rs.getString("Menu_Name");
			menu.setKind(list.get(i).get("Menu_Kind").toString());
			menu.setStatus(Integer.parseInt(list.get(i).get("Menu_Status").toString()));
			menu.setPrice(Integer.parseInt(list.get(i).get("Price").toString()));
			v.add(menu);
		}
		return v;
		
	}
	
	
	public boolean DeleteMenu(Menu _menu) {
		flag=false;
		int ID=_menu.getID();
		if(!super.IDfind("Menu",ID)) {
			System.out.println("该菜式不存在！");
			return false;
		}
		
		flag=super.DeleteDAO("menu",ID);
		return flag;
	}
	
	/*public Menu AutoInsert(Menu _menu) {
	menu=new Menu();
	
	
	return menu;
	}*/
	
}
