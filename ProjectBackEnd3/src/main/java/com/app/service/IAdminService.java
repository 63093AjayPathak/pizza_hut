package com.app.service;

import java.sql.Date;
import java.util.List;

//import com.app.entity.Employee;
import com.app.entity.FeedBack;
import com.app.entity.Order;
import com.app.entity.User;

public interface IAdminService {
// show all employees
	List<User> getAll();
	
//	method for creating a new employee
	User createNew(User employee);
	
//	method for removing existing employee
	void deleteEmployee(Long id);
	
//	method to fetch date-wise orders
	List<Order> dateWiseOrders(Date date);
	
//	to fetch all feedback
	List<FeedBack> showAllFeedbacks();
}
