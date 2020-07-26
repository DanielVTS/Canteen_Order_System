package cn.lingnan.view.model;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import cn.lingnan.DAO.MenuDAO;
import cn.lingnan.cart.ShoppingCar;
import cn.lingnan.dto.CartItem;
import cn.lingnan.dto.Menu;

public class CustomerMenuInfoTableModel extends AbstractTableModel {

	
	
	private static final long serialVersionUID = 1L;
	private List<Menu> l;
	private ShoppingCar cart;
	//private int[] IDmap;
	
	
	public CustomerMenuInfoTableModel(List<Menu> menuList,ShoppingCar _cart){
        this.l=menuList;
        this.cart=_cart;
    }
	
	
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return l.size();
	}

	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO 自动生成的方法存根
		//return null;
		String[] str = { "ID", "菜名", "菜类","状态","价格","数量"};
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
		if(columnIndex==5) return true; 
		return false;
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {


		Menu m=l.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return m.getID();
		case 1:
			return m.getName();
		case 2:
			return m.getKind();
		case 3:
			return m.getStatus();
		case 4:
			return m.getPrice();
		case 5:
			if(cart.getContainer()==null||cart.getContainer().get(m.getID())==null) return 0;
			else return cart.getContainer().get(m.getID()).getCount();
		default:
			return "出错！";
		}
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
        int count=Integer.parseInt(aValue.toString());
		if(count<0) count=0;
        MenuDAO md= new MenuDAO();
        int ID;
        if(cart.getIDmap()!=null) {
        	ID=cart.getIDmap()[rowIndex];
        }else ID=Integer.parseInt(this.getValueAt(rowIndex, 0).toString());
        
        
		
        Menu menu=md.FindMenu(ID);
		
		CartItem cartItem=new CartItem();
		int cartItemcount=0;
		if(cart!=null) {
			
			cartItem=cart.getContainer().get(ID);
			if(cartItem!=null) {
				cartItemcount=cartItem.getCount();
			}
		}
		int i=count-cartItemcount;
		if(i>0) {
			for(int j=0;j<i;j++) {
				cart.add(menu);
			}				
		}
		if(i<0)
		{
			for(int j=0;j>i;j--) {
				cart.delete(menu);
			}
		}
		
		
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
