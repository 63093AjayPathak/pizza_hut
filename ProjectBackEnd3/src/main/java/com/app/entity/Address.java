package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="address")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Address extends BaseEntity{

	@Column(name="house_number")
	@NotBlank(message="House Number cannot be blank")
	private String houseNumber;
	
	
	@Column(name="street_name", length=40)
	@NotBlank(message="Street name cannot be blank")
	private String streetName;
	
	@Column( length=20)
	@NotBlank(message="City cannot be blank")
	private String city;
	
	@Column( length=40)
	@NotBlank(message="State cannot be blank")
	private String state;
	
	@Column( length=6)
	@NotBlank(message="PINCODE cannot be blank")
	private String pincode;
	
	@ManyToOne
	@JoinColumn(name="customer_id",nullable=false)
	private User customer;
}
