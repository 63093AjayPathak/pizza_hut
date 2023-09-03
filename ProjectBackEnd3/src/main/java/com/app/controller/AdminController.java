package com.app.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BasketDTO;
//import com.app.entity.Employee;
import com.app.entity.FeedBack;
import com.app.entity.User;
import com.app.service.IAdminService;

@RestController
@Validated
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public AdminController() {
		// TODO Auto-generated constructor stub
		System.out.println("In Admin controller constructor ");
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/showAll")
	ResponseEntity<?> showAllEmployee(){
		
		List<User> emps = adminService.getAll();
		if (emps.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String("NO employees available"));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(emps);
		}
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addEmployee")
	ResponseEntity<?> addEmployee(@RequestBody User employee){
		
		
		String pass = employee.getPassword();
		
		String password = 	encoder.encode(pass);
		
		employee.setPassword(password);
		
		User emp = adminService.createNew(employee);
		
		return ResponseEntity.status(HttpStatus.OK).body(emp);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteEmployee/{id}")
	ResponseEntity<?> delEmp(@PathVariable @Valid @NotNull Long id){
		
		
			adminService.deleteEmployee(id);
			return ResponseEntity.status(HttpStatus.OK).body(new String("Employee with this id deleted"));
		
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/feedback")
	ResponseEntity<?> getAllFeedbacks(){
		
		List<FeedBack> list = adminService.showAllFeedbacks();
		
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String("No feedbacks available"));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getDateWiseOrders")
	ResponseEntity<?> getDatewiseOrders(@RequestBody BasketDTO basket){
		
		System.out.println(basket);
			
		return ResponseEntity.status(HttpStatus.OK).body(adminService.dateWiseOrders(basket.getOrderPlacedDate()));
	}
	
	
}
