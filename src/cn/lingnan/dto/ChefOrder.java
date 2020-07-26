package cn.lingnan.dto;

public class ChefOrder {
	private int Staff_Id;
	private int Menu_Id;
	private int Table_Id;
	private int Menu_count;
	private String Staff_Name;
	private String Menu_Name;
	
	
	public String getStaff_Name() {
		return Staff_Name;
	}
	public void setStaff_Name(String staff_Name) {
		Staff_Name = staff_Name;
	}
	public String getMenu_Name() {
		return Menu_Name;
	}
	public void setMenu_Name(String menu_Name) {
		Menu_Name = menu_Name;
	}
	public int getMenu_Id() {
		return Menu_Id;
	}
	public void setMenu_Id(int menu_Id) {
		Menu_Id = menu_Id;
	}
	public int getTable_Id() {
		return Table_Id;
	}
	public void setTable_Id(int table_Id) {
		Table_Id = table_Id;
	}
	public int getStaff_Id() {
		return Staff_Id;
	}
	public void setStaff_Id(int staff_Id) {
		Staff_Id = staff_Id;
	}
	public int getMenu_count() {
		return Menu_count;
	}
	public void setMenu_count(int menu_count) {
		Menu_count = menu_count;
	}
	
	

}
