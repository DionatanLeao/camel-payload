package com.camel.payload.service;

import java.util.Base64;

public class TokenService {

	public String gerarToken() {
		String token = Base64.getEncoder().encodeToString("Gerando token SSO".getBytes());
		return token;
	}

}
