package cn.lingnan.dto;

public class Order {
	private int Table_ID;
	private int Menu_ID;
	private int Menu_Count;
	private String Menu_Name;
	private int price;
	private int RowPrice;
	private int OrderStatus;
	private int bill;
	private String Staff_Name;
	public String getStaff_Name() {
		return Staff_Name;
	}
	public void setStaff_Name(String staff_Name) {
		Staff_Name = staff_Name;
	}
	public int getTable_ID() {
		return Table_ID;
	}
	public void setTable_ID(int table_ID) {
		Table_ID = table_ID;
	}
	public int getMenu_ID() {
		return Menu_ID;
	}
	public void setMenu_ID(int menu_ID) {
		Menu_ID = menu_ID;
	}
	public int getMenu_Count() {
		return Menu_Count;
	}
	public void setMenu_Count(int menu_Count) {
		Menu_Count = menu_Count;
	}
	public String getMenu_Name() {
		return Menu_Name;
	}
	public void setMenu_Name(String menu_Name) {
		Menu_Name = menu_Name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRowPrice() {
		return RowPrice;
	}
	public void setRowPrice(int rowPrice) {
		RowPrice = rowPrice;
	}
	public int getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		OrderStatus = orderStatus;
	}
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
	}
	


	

}
