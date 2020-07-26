package cn.lingnan.test;

import java.util.Vector;

import cn.lingnan.DAO.MenuDAO;
import cn.lingnan.dto.Menu;

public class MenuDAOTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		MenuDAO md=new MenuDAO();
		Menu menu=md.FindMenu(3);
		Vector<Menu> v=new Vector<>();
		v=md.findAllMenu();
		v=new Vector<>();
		v=md.find("Menu_Name", "可乐");
		System.out.println(v.get(0).getID());
		System.out.println(menu.getName());
		System.out.println(md.FindMenuExist(6));
		

	}

}
