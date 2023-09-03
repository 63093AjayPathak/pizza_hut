package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.FeedBack;

@Repository
public interface IFeedbackRepository extends JpaRepository<FeedBack, Long> {

//	creating a new feedback
	
//	getting all feedback
	

}
