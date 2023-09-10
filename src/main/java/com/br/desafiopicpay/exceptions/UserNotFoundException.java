package com.br.desafiopicpay.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 5353553538335581708L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
