package cn.lingnan.view.model;

import java.util.Map;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import cn.lingnan.cart.ShoppingCar;
import cn.lingnan.dto.CartItem;
//import cn.lingnan.DAO.ShoppingCar;
//import cn.lingnan.dto.CartItem;
import cn.lingnan.dto.Menu;

public class ShoppingCarTableModel extends AbstractTableModel{

	
	private static final long serialVersionUID = 1L;
	private CartItem cartItem=new CartItem();
	private ShoppingCar cart;
	//private MenuDAO md=new MenuDAO();
	private Menu menu;
	private Map<Integer, CartItem> map;
	private  Vector<CartItem> cartVec;
	
	public ShoppingCarTableModel(ShoppingCar _cart) {
		this.cart=_cart;
		if(!cart.getContainer().isEmpty()) {
			map=cart.getContainer();
			cartVec=new Vector<CartItem>();
			for(Map.Entry entry : map.entrySet()) {
	            cartVec.add((CartItem) entry.getValue());
	        }
		}
	}
		

	public CartItem getCartItem() {
		return cartItem;
	}


	

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if(cart.getContainer()==null) return 0;
		return cart.getContainer().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		if(!cartVec.isEmpty()) {			
			cartItem=cartVec.get(rowIndex);
		}
		
     	
     	
        switch(columnIndex) {
     	case 0:
     		return cartItem.getMenu().getName();	
     	case 1:
     		return cartItem.getCount();
     	case 2:
     		return cartItem.getCount()*cartItem.getMenu().getPrice();
     	case 3:
     		return "+";
     	case 4:
     		return "-";
     
     }
	
		return null;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		// TODO 自动生成的方法存根
		String[] str= {"菜名","数量","价格","+","-"};
		
		
		//return head[column];
		return str[columnIndex];	
	}
	
	public Class<?> getColumnClass(int columnIndex) {
		// TODO 自动生成的方法存根
		if(columnIndex==1||columnIndex==2) return Integer.class;
		else return String.class;
		
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		if(columnIndex==1) return true;
		else return false;
		
	}
	
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		String info = rowIndex + "行" + columnIndex + "列的值改变:" + aValue.toString();
		int count=Integer.parseInt(aValue.toString());
        if(count<0) count=0;
		CartItem cartItem=new CartItem();
		int cartItemcount=0;
		if(!cartVec.isEmpty()) {
			cartItemcount=cartVec.get(rowIndex).getCount();
			cartItem=cartVec.get(rowIndex);
			menu=cartItem.getMenu();
		}
		int i=count-cartItemcount;
		if(i>0) {
			for(int j=0;j<i;j++) {
				cart.add(menu);
			}				
		}else {
			for(int j=0;j>i;j--) {
				cart.delete(menu);
			}
		}
		
		//scd.add(md.FindMenu(rowIndex+1));
		//data[row][column] = aValue;
	    fireTableCellUpdated(rowIndex, 1);
	    fireTableCellUpdated(rowIndex, 2);
		javax.swing.JOptionPane.showMessageDialog(null, info);

	}

}
