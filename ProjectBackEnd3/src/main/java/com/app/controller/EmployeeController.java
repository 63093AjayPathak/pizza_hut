package com.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Order;
import com.app.entity.User;
import com.app.service.IEmployeeService;

@RestController
@Validated
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

	@Autowired
	private IEmployeeService empService;
	
	
	
	public EmployeeController() {
		// TODO Auto-generated constructor stub
		System.out.println("Inside EmployeeController constructor, dependency: "+empService);
	}
	

	@PreAuthorize("hasRole('SHOPKEEPER')")
	@PatchMapping("/changeStatus/{id}")
ResponseEntity<?> changeToPreparing(@PathVariable @Valid @NotNull Long id, @RequestBody Order order){
		
		
			Order ord = empService.changeStatus(id, order.getStatus());
			return ResponseEntity.status(HttpStatus.OK).body(ord);
		
		
		
	}
	
	
	
}
