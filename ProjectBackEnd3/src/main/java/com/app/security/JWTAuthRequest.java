package com.app.security;

import lombok.Data;

@Data
public class JWTAuthRequest {

	private String email;
	
	private String password;
}
