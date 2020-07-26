package cn.lingnan.test;

import cn.lingnan.DAO.TableDAO;
import cn.lingnan.dto.Table;

public class TableTest {

	public static void main(String[] args) {
		Table t=new Table();
		TableDAO td=new TableDAO();
//		System.out.println(td.FindAllTable());
		t.setTable_Id(101);
		t.setTable_Seat(12);
		t.setTable_Status(1);
		t.setPhone("123");
		System.out.println(td.EditTable(t));
//		System.out.println(td.AddTable(t));
//		System.out.println(td.Find("Phone", "123"));
	}
}
