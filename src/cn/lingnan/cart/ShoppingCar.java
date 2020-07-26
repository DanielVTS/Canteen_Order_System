package cn.lingnan.cart;

import java.util.HashMap;
import java.util.Map;

import cn.lingnan.dto.CartItem;
import cn.lingnan.dto.Menu;


public class ShoppingCar {
	private Integer TotalPrice=0;
	
	
	private Map<Integer, CartItem> container=new HashMap<Integer, CartItem>();
	
	public Map<Integer, CartItem> getContainer() {
		return container;
	}
	private int[] IDmap;
	
	public void setIDmap(int[] iDmap) {
		IDmap = iDmap;
	}
	public int[] getIDmap() {
		return IDmap;
	}
	public Integer add(Menu menu)
	{
		CartItem cartItem=container.get(menu.getID());
		
		if(cartItem==null)
		{
			cartItem=new CartItem();
			cartItem.setMenu(menu);
			cartItem.setCount(1);
			cartItem.setItemprice(menu.getPrice());
			container.put(menu.getID(), cartItem);
			this.TotalPrice=cartItem.getItemprice()+TotalPrice;
		}
		else
		{
			cartItem.setCount(cartItem.getCount()+1);
			cartItem.setItemprice(menu.getPrice()*cartItem.getCount());
			
				this.TotalPrice=cartItem.getItemprice()+TotalPrice;
				
		}
		return TotalPrice;
	}
	public Integer delete(Menu menu)
	{
		CartItem cartItem=container.get(menu.getID());
		if(cartItem!=null)
		{
			if(cartItem.getCount()==1)
			{
				container.remove(menu.getID());
				
					this.TotalPrice=cartItem.getItemprice()-TotalPrice;
					
			}
			else
			{
				cartItem.setCount(cartItem.getCount()-1);
				cartItem.setItemprice(menu.getPrice()*cartItem.getCount());
				
					this.TotalPrice=cartItem.getItemprice()-TotalPrice;
					
				
			}
		}
		return TotalPrice;
	}
	
	public Integer find(Menu menu) {
		CartItem cartItem=container.get(menu.getID());
		if(cartItem!=null)
		{
			if(cartItem.getCount()==1)
			{
				container.remove(menu.getID());
				
					this.TotalPrice=cartItem.getItemprice()-TotalPrice;
					
			}
			else
			{
				cartItem.setCount(cartItem.getCount()-1);
				cartItem.setItemprice(menu.getPrice()*cartItem.getCount());
				
					this.TotalPrice=cartItem.getItemprice()-TotalPrice;
					
				
			}
		}
		return menu.getID();
	}
	public Integer clear()
	{
		
		container.clear();
		this.TotalPrice=0;
			
		return TotalPrice;
	}
	
}

