package com.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;


@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Autowired
	private JWTTokenHelper tokenHelper;
	
	
//	this method will be called for each request
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
//		Step 1: get token from header
		String requestToken =request.getHeader("token");
		System.out.println(requestToken);
		
		String userName= null;
		
		String token=null;
		
		if(requestToken != null && requestToken.startsWith("Bearer") ) {
			
			token =requestToken.substring(7);// because token would be starting with "Bearer"
			
			try {
//				step 2: taking out the username form token
				userName= tokenHelper.getUserNameFromToken(token);
			}
			catch(IllegalArgumentException e) {
				System.out.println("Unable to get token");
			}
			catch(ExpiredJwtException e) {
				System.out.println("Jwt token has expired");
			}
			catch(MalformedJwtException e) {
				System.out.println("Invalid token");
			}
			
		}
		else {
			System.out.println("No token available");
		}
		
//		step 3: validate token
		if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
//			step 4: getting user from userName taken from token
			UserDetails userdetails = userDetailsService.loadUserByUsername(userName);
			
			if(tokenHelper.validateToken(token, userdetails)) {
//				if above condition is true then token has been verified and user is valid
				
// 			step 5: now we'll set the authentications
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userdetails,null, userdetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
			else{
			  System.out.println("Invalid token");	
			}
		}
		else {
			System.out.println("userName is null or context not null");
		}
		
		filterChain.doFilter(request, response);

	}

}
