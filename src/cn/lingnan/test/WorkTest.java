package cn.lingnan.test;

import cn.lingnan.DAO.ChefWorkDAO;
import cn.lingnan.dto.Work;

public class WorkTest {
public static void main(String[] args) {
	Work w=new Work();
	ChefWorkDAO wd=new ChefWorkDAO();
	w.setID(108);
	w.setName("111");
	w.setJob("2");	
//	System.out.println(wd.AddWork(w));
//	System.out.println(wd.DeleteWork(w));
//	System.out.println(wd.Find("Staff_Id",104));
	System.out.println(wd.FindAllWork());
}
}
