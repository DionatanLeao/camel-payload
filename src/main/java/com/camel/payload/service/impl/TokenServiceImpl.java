package com.camel.payload.service.impl;

import java.util.Base64;
import java.util.UUID;

import com.camel.payload.service.TokenService;

public class TokenServiceImpl implements TokenService {

	@Override
	public String gerarToken() {
		UUID uuid = UUID.randomUUID();
		String token = Base64.getEncoder().encodeToString(uuid.toString().getBytes());
		return token;
	}
}
