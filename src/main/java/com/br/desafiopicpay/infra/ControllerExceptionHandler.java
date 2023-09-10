package com.br.desafiopicpay.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.br.desafiopicpay.dtos.ExceptionDTO;
import com.br.desafiopicpay.exceptions.InsufficientBalanceException;
import com.br.desafiopicpay.exceptions.NotificationServiceUnavailableException;
import com.br.desafiopicpay.exceptions.UnauthorizedTransactionException;
import com.br.desafiopicpay.exceptions.UserNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler{

	private ExceptionDTO exception;

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ExceptionDTO> handleInsufficientBalanceException(InsufficientBalanceException e) {
		exception = new ExceptionDTO(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
	}

	@ExceptionHandler(NotificationServiceUnavailableException.class)
	public ResponseEntity<ExceptionDTO> handleNotificationServiceUnavailableException(
			NotificationServiceUnavailableException e) {
		exception = new ExceptionDTO(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception);
	}

	@ExceptionHandler(UnauthorizedTransactionException.class)
	public ResponseEntity<ExceptionDTO> handleUnauthorizedTransactionException(UnauthorizedTransactionException e) {
		exception = new ExceptionDTO(e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionDTO> handleUserNotFoundException(UserNotFoundException e) {
		exception = new ExceptionDTO(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionDTO> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		exception = new ExceptionDTO("already registered user");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
	}
}
