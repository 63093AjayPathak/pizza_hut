package com.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.CustomException;
import com.app.dto.BasketDTO;
import com.app.dto.PostOrderRequest;
import com.app.entity.Address;
//import com.app.entity.Customer;
import com.app.entity.FeedBack;
import com.app.entity.Menu;
import com.app.entity.Order;
import com.app.entity.OrderDetails;
import com.app.entity.User;
import com.app.repository.IAddressRepository;
//import com.app.repository.ICustomerRepository;
import com.app.repository.IFeedbackRepository;
import com.app.repository.IMenuRepository;
import com.app.repository.IOrderDetailsReposiory;
import com.app.repository.IOrderRepository;
import com.app.repository.IUserRepository;


@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	
	
	@Autowired
	private IAddressRepository addRepo;
	
	@Autowired
	private IMenuRepository menuRepo;
	
	@Autowired
	private IFeedbackRepository feedbackRepo;
	
	@Autowired
	private IOrderRepository orderRepo;
	
	@Autowired
	private IOrderDetailsReposiory ordDetailsRepo;
	
	@Autowired
	private IUserRepository userRepo;
	
	
	
	@Override
	public User signup(User customer) {
		
		customer.setRole("CUSTOMER");
		
		return userRepo.save(customer);
	}


	@Override
	public Address addAddress(Address address, Long id) {
		
		User cust =  userRepo.findById(id).orElseThrow(()-> new RuntimeException("No customer wih given id"));
		address.setCustomer(cust);
		return addRepo.save(address);
	}


	@Override
	public User signin(String email, String password) {
		
		User cust =  userRepo.findByEmailAndPassword(email, password).orElseThrow(()-> new RuntimeException("No Such customer"));
		
		if(cust.getRole().equals("CUSTOMER"))
		return cust;
		
		else
			throw new CustomException("Invalid Customer details");
	}


	@Override
	public List<Menu> fetchMenu() {
		
		return menuRepo.findByAvailability(true);
	}


	@Override
	public FeedBack giveFeedback(FeedBack feedback, Long id) {
		
		User cust = userRepo.findById(id).orElseThrow(()-> new RuntimeException("No customer with such id"));
		feedback.setCustomer(cust);
		return feedbackRepo.save(feedback);
	}


	@Override
	public User editDetails(User customer, Long id) {
		User cust = userRepo.findById(id).orElseThrow(()-> new RuntimeException("Couldn't find customer with given id"));
		
		if(customer.getName() != null) {
			cust.setName(customer.getName());
		}
		if(customer.getMobile() != null) {
			cust.setMobile(customer.getMobile());
		}
		if(customer.getEmail() != null) {
			cust.setEmail(customer.getEmail());
		}
		if(customer.getPassword() != null) {
			cust.setPassword(customer.getPassword());
		}
				
		return cust;
	}


	@Override
	public List<Address> showAllAddress(Long id) {
	
		User cus = userRepo.findById(id).orElseThrow(()-> new RuntimeException("Customer with given id cannot be found"));
	
		return addRepo.findAllByCustomer(cus);
	}


	@Override
	public User fetchCustomer(Long id) {
	 User cus = userRepo.findById(id).orElseThrow(()-> new RuntimeException("Customer with given id cannot be found"));
		return cus;
	}


	@Override
	public List<Order> fetchAllOrders(Long id) {
		User cus = userRepo.findById(id).orElseThrow(()-> new RuntimeException("Customer with given id cannot be found")); 
		
		return orderRepo.findByCustomerOrderByOrderPlacedTimeDesc(cus);
	}


	@Override
	public List<OrderDetails> getOrderDetails(Long id) {
		
		Order ord = orderRepo.findById(id).orElseThrow(()-> new RuntimeException("No order with given id"));
		
		return ordDetailsRepo.findByOrder(ord);
	}


	@Override
	public String postrOrder(Long id, BasketDTO basket) {
		
		        Order order = new Order();
		        
		        User cus = userRepo.findById(id).orElseThrow(()-> new CustomException("Customer couldn't be found"));
		        
		        Address address = addRepo.findById(basket.getAddressId()).orElseThrow(()-> new CustomException("Address couldn't be found"));
		        
		        order.setCustomer(cus);
		        order.setAddress(address);
		        order.setTotalAmount(basket.getAmount());
		        order.setOrderPlacedTime(LocalDateTime.now());
		        order.setStatus("PLACED");
		        
		         order =orderRepo.save(order);
		        		       
		        
		        for (PostOrderRequest p : basket.getItems()) {
		        	
		        	OrderDetails orderd= new OrderDetails();
		        	
		        	Menu item= menuRepo.findById(p.getItemId()).orElseThrow(()-> new CustomException("Item with item id : "+p.getItemId()+" couldn't be found"));
		        	
		        	orderd.setItem(item);
		        	orderd.setOrder(order);
		        	orderd.setQuantity(p.getQuantity());
		        	ordDetailsRepo.save(orderd);
		        }
		        
		        
		        return "Posting Order was a success";
		
	}


	@Override
	public User getByEmail(String email) {
		User user = userRepo.findByEmail(email).orElseThrow(()->new CustomException("No User Found wiht given email"));
		return user;
	}

}
