package com.app.service;

import java.util.List;

import com.app.entity.Menu;
import com.app.entity.Order;

public interface IShopkeeperService {

// method for viewing current orders (orders with status as 'PLACED')
List<Order> getPlacedOrders(String status);	

//method for changing status of a order

	
// method for viewing current orders (orders with status as 'PREPARING')
	
	
//	showing full menu before altering the item's availability
	List<Menu> showMenu();
	
	
//	method for altering menu
	Menu changeAvailability(Long id);
}
