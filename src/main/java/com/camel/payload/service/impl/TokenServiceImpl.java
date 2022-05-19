package com.camel.payload.service.impl;

import java.util.Base64;

import com.camel.payload.service.TokenService;

public class TokenServiceImpl implements TokenService {
	
	@Override
	public String gerarToken() {
		String token = Base64.getEncoder().encodeToString("Gerando token SSO".getBytes());
		return token;
	}
}
