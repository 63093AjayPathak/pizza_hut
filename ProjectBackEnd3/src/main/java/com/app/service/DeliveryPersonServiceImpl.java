package com.app.service;

import java.util.List;

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
public class DeliveryPersonServiceImpl implements IDeliverypersonService {
	

	@Autowired
	private IOrderRepository orderRepo;
	
	
	@Autowired
	private IUserRepository userRepo;
	
	
	@Override
	public List<Order> newOrders() {
		
		return orderRepo.findByStatusAndDeliveryPerson("PREPARING", null);
	}
	@Override
	public String selectOrder(Long EmpId, Long orderId) {
		
	 User emp =userRepo.findById(EmpId).orElseThrow(()-> new CustomException("Delivery person with given Id couldn't be found"));
		
		Order order = orderRepo.findById(orderId).orElseThrow(()-> new CustomException("Order with given Id couldn't be found"));
		
		order.setDeliveryPerson(emp);
		
		return "Order select fro delivery";
	}

}
