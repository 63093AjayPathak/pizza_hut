package com.app.security;

import lombok.Data;

@Data
public class JWTAuthResponse {

	private Long id;
	private String token;
}
