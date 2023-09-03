package com.app.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.entity.Order;
import com.app.service.IDeliverypersonService;

@RestController
@Validated
@RequestMapping("/delivery")
@CrossOrigin
public class DeliverController {
	
	@Autowired
	private IDeliverypersonService deliveryService;
	
	public DeliverController() {
		// TODO Auto-generated constructor stub
		System.out.println("Inside constructor of Delivery controller");
	}
	
	
	//@PreAuthorize("hasRole('DELIVERYPERSON')")
	@GetMapping("/getNewOrders")
	ResponseEntity<?> getNewOrders(){
		
		List<Order> list = deliveryService.newOrders();
		
		if(list.isEmpty()) {
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String("No new Orders"));
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
	}
	
	//@PreAuthorize("hasRole('DELIVERYPERSON')")
	@PatchMapping("/selectOrder/{id}")
	ResponseEntity<?> selectOrderForDelivery(@PathVariable @Valid @NotNull Long id, @RequestBody Order order){
		
		
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body( new ApiResponse(deliveryService.selectOrder(id, order.getId())));
	}

}
