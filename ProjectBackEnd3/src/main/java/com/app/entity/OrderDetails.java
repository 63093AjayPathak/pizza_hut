
package com.app.entity;

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

@Table(name = "order_details")

@Getter

@Setter

@ToString(exclude = "order")

@NoArgsConstructor
public class OrderDetails extends BaseEntity {

	@Column(nullable = false)
	private Integer quantity;

	@ManyToOne //(fetch = FetchType.LAZY)

	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne

	@JoinColumn(name = "item_id", nullable = false)
	private Menu item;
}
