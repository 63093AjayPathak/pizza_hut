package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.CustomException;
import com.app.entity.User;
import com.app.repository.IUserRepository;


@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private IUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		// loading user info from database by username (i.e, email in this case)
		User user = userRepo.findByEmail(username).orElseThrow(()->new CustomException("User Not Found"));
		
		
		
		return new CustomUserDetails(user);
	}

	
	
}
