package cn.lingnan.DAO;

import java.util.HashMap;
import java.util.Map;

import cn.lingnan.dto.CarItem;
import cn.lingnan.dto.Menu;


public class ShoppingCarDAO {
	private Integer TotalPrice=0;


	
	private Map<Integer, CarItem> container=
			new HashMap<Integer, CarItem>();
	
	public Integer add(Menu menu)
	{
		CarItem carItem=container.get(menu.getID());
		
		if(carItem==null)
		{
			carItem=new CarItem();
			carItem.setMenu(menu);
			carItem.setCount(1);
			carItem.setItemprice(menu.getPrice());		
			this.TotalPrice=carItem.getItemprice()+TotalPrice;
		}
		else
		{
			carItem.setCount(carItem.getCount()+1);
			carItem.setItemprice(menu.getPrice()*carItem.getCount());
			
				this.TotalPrice=carItem.getItemprice()+TotalPrice;
				
		}
		return TotalPrice;
	}
	public Integer delete(Menu menu)
	{
		CarItem carItem=container.get(menu.getID());
		if(carItem!=null)
		{
			if(carItem.getCount()==1)
			{
				container.remove(menu.getID());
				
					this.TotalPrice=carItem.getItemprice()-TotalPrice;
					
			}
			else
			{
				carItem.setCount(carItem.getCount()-1);
				carItem.setItemprice(menu.getPrice()*carItem.getCount());
				
					this.TotalPrice=carItem.getItemprice()-TotalPrice;
					
				
			}
		}
		return TotalPrice;
	}
	public Integer clear()
	{
		
		container.clear();
		this.TotalPrice=0;
			
		return TotalPrice;
	}
	
}

