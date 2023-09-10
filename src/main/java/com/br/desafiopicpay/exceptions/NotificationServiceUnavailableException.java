package com.br.desafiopicpay.exceptions;

public class NotificationServiceUnavailableException extends RuntimeException{

	private static final long serialVersionUID = 1726575778797867852L;

	public NotificationServiceUnavailableException(String message) {
		super(message);
	}
}
