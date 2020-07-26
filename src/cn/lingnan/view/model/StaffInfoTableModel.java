package cn.lingnan.view.model;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import cn.lingnan.dto.Staff;

public class StaffInfoTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;
	private List<Staff> l;
	
	public StaffInfoTableModel(List<Staff> staffList){

        this.l=staffList;
        

    }
	
	
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return l.size();
	}

	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return 5;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO 自动生成的方法存根
		//return null;
		String[] str = { "ID", "人名", "职位","性别","密码"};
		return str[columnIndex];		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO 自动生成的方法存根
		if(columnIndex==1||columnIndex==2) return String.class;
		else return Integer.class;
		
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		//if(columnIndex!=0) return true;
		//else 
			return false;
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {


		Staff m=l.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return m.getID();
		case 1:
			return m.getName();
		case 2:
			return m.getJob();
		case 3:
			return m.getGender();
		case 4:
			return m.getPassword();	
		default:
			return "出错！";
		}
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		String info = rowIndex + "行" + columnIndex + "列的值改变:" + aValue.toString();

		javax.swing.JOptionPane.showMessageDialog(null, info);

	}

	

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO 自动生成的方法存根
		

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO 自动生成的方法存根

	}

	public void refresh() {
		this.fireTableDataChanged();
	}
}
