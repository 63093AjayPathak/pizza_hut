package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="menu")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Menu extends BaseEntity{

	@Column(name="item_name")
	@NotBlank(message="Item name cannot be blank")
	private String itemName;
	
	private Boolean availability;
	
	
	
	private Double price;
	
	@Column(length=50)
	private String description;
	
	@Column(length=15)
	private String size;
}
