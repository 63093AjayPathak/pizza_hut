package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Menu;

@Repository
public interface IMenuRepository extends JpaRepository<Menu, Long> {

//	getting all menu items based on availability
	List<Menu> findByAvailability(Boolean b);
	
// changing specific item's availability in menu
	
}
