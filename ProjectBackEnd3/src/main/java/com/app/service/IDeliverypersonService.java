package com.app.service;

import java.util.List;

import com.app.entity.Order;

public interface IDeliverypersonService {

//	method for fetching all orders where employee_id is null ( i.e, they haven't been accepted by any other delivery boy)
	List<Order> newOrders();
	
//	method to accept an order ( basically insert  current employee's employee_id in current order)
	String selectOrder(Long EmpId,Long orderId );
	
//	method to show all orders accepted by specific employee (orders with eployee_id equals to specific employee)
	
	
}
