package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity{

	@Column(length=40, nullable=false)
	@NotBlank(message="Name must be supplied")
	private String name;
	
	
	@Column(length=15)
	private String mobile;
	
	@Column(unique =true, length=40)
	@NotBlank(message="Email cannot be blank")
	@Email(message="Invalid email format")
	private String email;
	
	@Column(length=100, nullable=false)
	@NotBlank(message="Password must be supplied")
	@JsonIgnore
	private String password;
	
	@Column(nullable=false)
	private String role;
	
}
