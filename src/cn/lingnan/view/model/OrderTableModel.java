package cn.lingnan.view.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import cn.lingnan.dto.Order;



public class OrderTableModel extends AbstractTableModel{

	
	private static final long serialVersionUID = 1L;

	private String [] columnName = {"员工姓名","座台号","菜名","菜品数量"};
    
    //List<ChefOrder> query() ;
	private List<Order> l;
	public OrderTableModel(Vector<Order> v){

        this.l=v;
        

    }

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return l.size();
	}

	@Override
	public int getColumnCount() {
		
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Order order=l.get(rowIndex);
		if(columnIndex==0) {
			return order.getStaff_Name();
		}else if(columnIndex==1) {
			return order.getTable_ID();
		}else if(columnIndex==2) {
			return order.getMenu_Name();
		}else if(columnIndex==3) {
			return order.getMenu_Count();
		}else {
			return null;
		}
		
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		String info = rowIndex + "行" + columnIndex + "列的值改变:" + aValue.toString();

		javax.swing.JOptionPane.showMessageDialog(null, info);

	}
	
	
	@Override
	public String getColumnName(int column) {
		return columnName[column];
	}
}
