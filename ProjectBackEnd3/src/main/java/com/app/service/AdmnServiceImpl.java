package com.app.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.app.entity.Employee;
import com.app.entity.FeedBack;
import com.app.entity.Order;
import com.app.entity.User;
//import com.app.repository.IEmployeeRepository;
import com.app.repository.IFeedbackRepository;
import com.app.repository.IOrderRepository;
import com.app.repository.IUserRepository;

@Service
@Transactional
public class AdmnServiceImpl implements IAdminService {

	
	
	@Autowired
	private IFeedbackRepository feedbackRepo;
	
	@Autowired
	private IOrderRepository orderRepo;
	
	@Autowired
	private IUserRepository userRepo;
	
	
	@Override
	public List<User> getAll() {
		
		return userRepo.getAllEmployees("SHOPKEEPER", "DELIVERYPERSON");
	}


	@Override
	public User createNew(User employee) {
		
		return userRepo.save(employee);
	}


	@Override
	public void deleteEmployee(Long id) {
		
		
			User emp = userRepo.findById(id).orElseThrow(()-> new RuntimeException("Employee with specified id couldn't be found"));
			
			userRepo.delete(emp);
		
		
		
	}


	@Override
	public List<FeedBack> showAllFeedbacks() {
		
		return feedbackRepo.findAll();
	}


	@Override
	public List<Order> dateWiseOrders(Date date) {
		// TODO Auto-generated method stub
		return orderRepo.searchingViaOrderPlaceddate(date);
	}

}
