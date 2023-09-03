
package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Order;
import com.app.entity.OrderDetails;

@Repository
public interface IOrderDetailsReposiory extends JpaRepository<OrderDetails, Long> {

	// setting order details (order ID , Item ID, Quantity)

	// fetching order details for a specific orderID
	List<OrderDetails> findByOrder(Order order);
}
