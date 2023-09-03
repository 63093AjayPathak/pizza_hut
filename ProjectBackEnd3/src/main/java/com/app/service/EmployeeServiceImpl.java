package com.app.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.CustomException;
import com.app.entity.Order;
import com.app.entity.User;
//import com.app.repository.IEmployeeRepository;
import com.app.repository.IOrderRepository;
import com.app.repository.IUserRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

	
	
	
	@Autowired
	private IOrderRepository orderRepo;
	
	@Autowired 
	private IUserRepository userRepo;

	
	@Override
	public User signIn(String email, String password) {
		
		User employee=userRepo.findByEmailAndPassword(email, password).orElseThrow(()-> new CustomException("Employee Not Found"));
		
		if(!employee.getRole().equals("CUSTOMER"))
			return employee;
		
		else
			throw new CustomException("Invalid employee login");
			
		
	}
	
	@Override
	public Order changeStatus(Long id, String status) {
		
		Order order = orderRepo.findById(id).orElseThrow(() -> new CustomException("No order with given id"));
		
		if(status.equals("OUT FOR DELIVERY")) {
			
			if(order.getDeliveryPerson()== null) {
				throw new CustomException("Cannot change status , this order has not been accepted for delivery");
			}
			
		}
		
		if(status.equals("DELIVERED")) {
			if(!order.getStatus().equals("OUT FOR DELIVERY")) {
				throw new CustomException("Cannot change status , this order is still being prepared");
			}
			
			order.setDeliveryTime(LocalDateTime.now());
		}

		

		order.setStatus(status);

		return order;
	}


}
