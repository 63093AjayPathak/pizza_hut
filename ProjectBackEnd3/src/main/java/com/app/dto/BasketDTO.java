package com.app.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BasketDTO {

	private Date orderPlacedDate;
	
	private Long addressId;
	
	private Double amount;
		
	private List<PostOrderRequest> items;
}
