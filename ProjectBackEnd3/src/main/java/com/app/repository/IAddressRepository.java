package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Address;
import com.app.entity.User;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {

//	getting address via specific customer
	List<Address> findAllByCustomer(User cus);
	
//	editing an address
	
//	getting specific address via id
}
