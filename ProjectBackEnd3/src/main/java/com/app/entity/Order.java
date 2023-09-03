
package com.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

@Table(name = "orders")

@Getter

@Setter

@ToString

@NoArgsConstructor
public class Order extends BaseEntity {

	@Column(name = "order_placed_time", nullable = false)
	private LocalDateTime orderPlacedTime;

	@Column(nullable = false)
	private String status;

	@Column(nullable = false, name="total_amount")
	private Double totalAmount;

	@Column(name = "delivery_time")
	private LocalDateTime deliveryTime;

	@ManyToOne

	@JoinColumn(name = "customer_id", nullable = false)
	private User customer;

	@ManyToOne 
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private User deliveryPerson;

}
