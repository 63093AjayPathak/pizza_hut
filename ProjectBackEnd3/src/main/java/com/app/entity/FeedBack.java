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
@Table(name="feedback")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FeedBack extends BaseEntity{

	@Column(nullable=false)
	@NotBlank(message="feedback cannot be null")
	private String feedback;
	
	
	@ManyToOne
	@JoinColumn(nullable=false, name="customer_id")
	private User customer;
}
