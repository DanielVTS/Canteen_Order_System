package cn.lingnan.dto;

public class Menu {
	//Menu_ID  	 Menu_Name 	 Menu_Kind(属于肉类/蔬菜/饮料)  
	//Status(餐品状态默认为1) 	 Price     Count（选择餐品数量）
	
	private int ID;
	private String Name;
	private String Kind;
	private int Status;
	private int Price;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getKind() {
		return Kind;
	}
	public void setKind(String kind) {
		Kind = kind;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	
	


}
