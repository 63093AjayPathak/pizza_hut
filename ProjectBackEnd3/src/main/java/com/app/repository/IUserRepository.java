package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//import com.app.entity.Customer;
import com.app.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

//	getting a customer via id
//	--done--
	
	
//	method for customer signup
//	--done--
	
//	find all users with role as shopkeeper and deliveryperson
	@Query("select u from User u where u.role=?1 OR u.role=?2")
	List<User> getAllEmployees(String role1, String rol2);
	
//	finding by email (username) for userdetailsService
	Optional<User> findByEmail(String email);
	
	
//	method for customer login via email and password
	Optional<User> findByEmailAndPassword(String email, String password);
	

//	method for editing customer details
//	--done--
	
}
