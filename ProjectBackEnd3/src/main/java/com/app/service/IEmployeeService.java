package com.app.service;

import com.app.entity.Order;
import com.app.entity.User;

public interface IEmployeeService {
	
//	method for employee signin
	User signIn(String email, String password);
	
//	method for changing status of an order
	Order changeStatus(Long id, String status);
	

}
