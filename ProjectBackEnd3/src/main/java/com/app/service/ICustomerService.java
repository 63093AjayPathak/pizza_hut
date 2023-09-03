package com.app.service;

import java.util.List;

import com.app.dto.BasketDTO;
import com.app.entity.Address;
//import com.app.entity.Customer;
import com.app.entity.FeedBack;
import com.app.entity.Menu;
import com.app.entity.Order;
import com.app.entity.OrderDetails;
import com.app.entity.User;

public interface ICustomerService {
//	fetch user details by email
	User getByEmail(String email);

//	method for user signin
	User signin(String email, String password);
	
//	method for user signup
	User signup(User customer);
	
//	method for adding a new address
	Address addAddress(Address address, Long id);
	
//	posting feedback
	FeedBack giveFeedback(FeedBack feedback, Long id);
	
//	fetching customer details with id
	User fetchCustomer(Long id);
	
//	method for editing customer details
	User editDetails(User customer  ,Long id);
	
//	method for fetching all addresses of current customer before proceeding for payment
	List<Address> showAllAddress(Long id);
	
//	method for editing existing address
	
//	method for fetching the menu
	List<Menu> fetchMenu();
	
	
//	method for saving a specific order in orders table + order_details table
	String postrOrder(Long id, BasketDTO basket);
	
	
//	method for fetching previous orders of a specific customer
	List<Order> fetchAllOrders(Long id);
	
//	method for fetching order details of a specific order
	List<OrderDetails> getOrderDetails(Long id);
	
//	method to cancel an order
	
}
