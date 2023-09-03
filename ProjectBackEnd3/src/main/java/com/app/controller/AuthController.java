package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.custom_exception.CustomException;
import com.app.dto.ApiResponse;
import com.app.entity.User;
import com.app.security.JWTAuthRequest;
import com.app.security.JWTAuthResponse;
import com.app.security.JWTTokenHelper;
import com.app.service.ICustomerService;

@RestController
@RequestMapping("/user")
public class AuthController {
	
	@Autowired
	private JWTTokenHelper tokenHelper;
		
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private ICustomerService custService;
	
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> createToken( @RequestBody JWTAuthRequest requestBody) {
		
		authenticate(requestBody.getEmail(), requestBody.getPassword());
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(requestBody.getEmail());
		
		String token = tokenHelper.generateToken(userDetails);
		
		User user = custService.getByEmail(requestBody.getEmail());
		
		JWTAuthResponse response = new JWTAuthResponse();
		response.setToken(token);
		response.setId(user.getId());
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	

	private void authenticate(String userName, String password) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);
		
		try {
			authenticationManager.authenticate(authenticationToken);
		}
		catch(DisabledException e) {
			throw new CustomException("User is disabled");
		}
		
		
	}
	
	@PostMapping("/signup")
	ResponseEntity<?> newCustomer(@RequestBody User user){
		
		String pass = user.getPassword();
			
		String password = 	encoder.encode(pass);
		
		user.setPassword(password);
		
		System.out.println(custService.signup(user));
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("New Customer created"));
	}
	
}
