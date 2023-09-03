package com.app.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import com.app.entity.Employee;
import com.app.entity.Order;
import com.app.entity.User;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

	// method to add a order
//	--done--

	// method to get a order by id
//	--done--

	// method to update order status
//	--done--

	// getting all orders for a specific customer ordered by time in descreasing order
	List<Order> findByCustomerOrderByOrderPlacedTimeDesc(User cust);	
	
//t
	

	// getting all orders by date
	@Query(
			value="select * from orders where Date(order_placed_time) = ?1",
			nativeQuery=true
			)
	List<Order> searchingViaOrderPlaceddate(Date date);
	
	
//	getting all orders with specific status
	List<Order> findByStatus(String status);
	
//	getting all orders with status as 'PREPARING' and employee_id as null
	List<Order> findByStatusAndDeliveryPerson(String status, User emp);
	
	
//	to set employee id for a order (deliveryperson accepting an order)
//	--done--
}
