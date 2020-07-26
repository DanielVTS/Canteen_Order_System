package cn.lingnan.dto;

import cn.lingnan.dto.Menu;


public class CartItem {


	private Integer count=0;
	private Integer itemprice=0;
	private Menu menu;
	
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getItemprice() {
		return itemprice;
	}
	public void setItemprice(int itemprice) {
		this.itemprice = itemprice;
	}
	
}
