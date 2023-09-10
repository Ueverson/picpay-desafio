package com.br.desafiopicpay.exceptions;

public class UnauthorizedTransactionException extends RuntimeException {

	private static final long serialVersionUID = 5228648379498545434L;
	
	public UnauthorizedTransactionException(String message) {
		super(message);
	}
	
}
