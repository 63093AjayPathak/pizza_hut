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

import com.app.entity.Menu;
import com.app.entity.Order;
import com.app.service.IShopkeeperService;

@RestController
@Validated
@RequestMapping("/shopkeeper")
@CrossOrigin
public class ShopkeeperController {
	
	@Autowired
	private IShopkeeperService shopkeeperService;
	
	public ShopkeeperController() {
		// TODO Auto-generated constructor stub
		System.out.println("In shopkeeper controller");
	}
	
	//@PreAuthorize("hasRole('SHOPKEEPER')")
	@GetMapping("/showMenu")
	ResponseEntity<?> showFullMenu(){
		
		return ResponseEntity.status(HttpStatus.OK).body(shopkeeperService.showMenu());
	}
	
	//@PreAuthorize("hasRole('SHOPKEEPER')")
	@PatchMapping("/alterMenu/{id}")
	ResponseEntity<?> alterMenu(@PathVariable @Valid @NotNull Long id){
		
		
			Menu item = shopkeeperService.changeAvailability(id);
			return ResponseEntity.status(HttpStatus.OK).body(item);
		
		
		
	}
	
	//@PreAuthorize("hasRole('SHOPKEEPER')")
	@GetMapping("/getOrdersWithCertainStatus")
	ResponseEntity<?> getPlacedOrders(@RequestBody Order order){
		String status = order.getStatus().toUpperCase();
		
		List<Order> list = shopkeeperService.getPlacedOrders(status);
		
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new String("No new orders") );
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
	}
	

	
}
