package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.BasketDTO;
import com.app.entity.Address;
//import com.app.entity.Customer;
import com.app.entity.FeedBack;
import com.app.entity.Menu;
import com.app.entity.Order;
import com.app.entity.OrderDetails;
import com.app.entity.User;
import com.app.service.CustomerServiceImpl;

@RestController
@Validated
@RequestMapping("/customer")
@CrossOrigin //(origins = "http://localhost:3000")
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl custService;
	
	public CustomerController() {
		System.out.println("In Customer controller constructor");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("In init() of customer controller , dependency "+custService);
	}
	
	

	//@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/addAddress/{id}")
	public ResponseEntity<?> addNewAddress(@RequestBody @Valid Address address,@PathVariable @Valid @NotNull Long id, HttpSession session){
			
			
			Address add = custService.addAddress(address, id);
		
			return ResponseEntity.status(HttpStatus.CREATED).body(add);

	}
	
	//@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/menu")
	public ResponseEntity<?> showMenu() {
		
		List<Menu> menu = custService.fetchMenu();
		
		if(menu.isEmpty()) {
			ApiResponse res=new ApiResponse("List empty hai!!");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(menu);
		}
		
	}
	
	
	@GetMapping("/signout")
	public void logout(HttpSession session) {
		
		session.invalidate();		
	}
	
	//@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/feedback/{id}")
	ResponseEntity<?> addfeedback(@RequestBody FeedBack feedback, @PathVariable Long id){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(custService.giveFeedback(feedback, id));
		}
		catch(RuntimeException r) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String ("Customer with given id couldn't be found"));
		}
	}
	
	//@PreAuthorize("hasRole('CUSTOMER')")
	@PatchMapping("/editProfile/{id}")// try it again with postman 
	ResponseEntity<?> editProfile (@RequestBody User customer, @PathVariable @Valid @NotNull Long id){
		
		try {
			User cust = custService.editDetails(customer, id);
			return ResponseEntity.status(HttpStatus.OK).body(cust);
			
		}
		catch(RuntimeException r) {
			
			System.out.println(r.getMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String ("Customer with given id couldn't be found"));
		}
		
	}
	
	//@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/showAddress/{id}")
	ResponseEntity<?> getAllAddress(@PathVariable @Valid @NotNull Long id){
		
		try {
			List<Address> list = custService.showAllAddress(id);
			
			if(list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.FOUND).body(new String ("No addresses for given customer"));
			}
			else {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			}
			
			
		}
		catch(RuntimeException r) {
			System.out.println(r.getMessage());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String ("No customer with given customer id"));
		}
		
	}
	
	//@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/showProfile/{id}")
	ResponseEntity<?> showProfile(@PathVariable @Valid @NotNull Long id){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(custService.fetchCustomer(id));
		}
		catch(RuntimeException r) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String ("No customer with given customer id"));
		}
	}
	
	//@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/showOrders/{id}")
	ResponseEntity<?>showPreviousOrders(@PathVariable @Valid @NotNull Long id){
//		fetch all orders fo a given customer and store them in  a list

		try {
			List <Order> orders = custService.fetchAllOrders(id);
			if(orders.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String ("No orders for current customer"));
			}
			else {
				return ResponseEntity.status(HttpStatus.OK).body(orders);
			}
		}
		catch(RuntimeException r) {
			r.printStackTrace();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String (new String(r.getMessage())));
		}
	}
	
	//@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/orderDetails/{id}")
	ResponseEntity<?> getOrderDetails(@PathVariable @Valid @NotNull Long id){
		
		try {
			List<OrderDetails> list =custService.getOrderDetails(id);
			
			if(list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String ("No orders for current customer"));
			}
			else {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			}
		}
		catch(RuntimeException r) {
			r.printStackTrace();
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String (new String(r.getMessage())));
		}
	}
	
	//@PreAuthorize("hasRole('CUSTOMER')")
	@PostMapping("/postOrder/{id}")
	ResponseEntity<?> saveOrderDetails(@PathVariable @Valid @NotNull Long id, @RequestBody BasketDTO basket){
		
		//System.out.println(Request);
		
		
		System.out.println("request received: "+basket);
		System.out.println("Customer id received: "+id);
		
		System.out.println(custService.postrOrder(id, basket));   
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Kaam ho gya bhai"));
	}
}
	
