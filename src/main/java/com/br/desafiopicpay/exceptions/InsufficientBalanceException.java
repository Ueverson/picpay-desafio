package com.br.desafiopicpay.exceptions;

public class InsufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = 7155501261471788027L;
	
	public InsufficientBalanceException(String message) {
		super(message);
	}

}
