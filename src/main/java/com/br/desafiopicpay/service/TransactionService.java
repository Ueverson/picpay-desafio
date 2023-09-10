package com.br.desafiopicpay.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.br.desafiopicpay.domain.transaction.Transaction;
import com.br.desafiopicpay.domain.user.User;
import com.br.desafiopicpay.dtos.TransactionDTO;
import com.br.desafiopicpay.exceptions.UnauthorizedTransactionException;
import com.br.desafiopicpay.repositories.TransactionRepository;

@Service
public class TransactionService {

	private TransactionRepository repository;
	private UserService userService;
	private RestTemplate restTemplate;
	private NotificationService notificationService;

	@Value("${authorizer.url}")
	private String urlAuthorizer;
	private static final String MESSAGE_BODY = "message";
	private static final String AUTHORIZED = "Autorizado";

	public TransactionService(TransactionRepository repository, UserService userService, RestTemplate restTemplate,
			NotificationService notificationService) {
		this.repository = repository;
		this.userService = userService;
		this.restTemplate = restTemplate;
		this.notificationService = notificationService;
	}

	public Transaction createTransaction(TransactionDTO transactionDTO){
		User sender = this.userService.findUserById(transactionDTO.senderId());
		User receiver = this.userService.findUserById(transactionDTO.receiverId());

		userService.validateTransaction(sender, transactionDTO.value());

		if (!authorizeTransaction()) {
			throw new UnauthorizedTransactionException("Transaction was not authorized");
		}

		Transaction transaction = createInstanceTransaction(transactionDTO, sender, receiver);
		this.userService.updateBalance(sender, receiver, transactionDTO.value());
		
		this.notificationService.sendNotification(sender,"transaction to ".concat(receiver.getName()).concat(" sent successfully"));
		this.notificationService.sendNotification(receiver,sender.getName().concat(" transaction received successfully "));
		
		return transaction;
	}

	private Transaction createInstanceTransaction(TransactionDTO transactionDTO, User sender, User receiver) {
		return this.repository
				.save(new Transaction(null, transactionDTO.value(), sender, receiver, LocalDateTime.now()));
	}

	public boolean authorizeTransaction() {
		ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(urlAuthorizer, Map.class);

		if (HttpStatus.OK.equals(authorizationResponse.getStatusCode())) {
			Map<String, Object> responseBody = authorizationResponse.getBody();

			if (responseBody != null && responseBody.containsKey(MESSAGE_BODY)) {
				return AUTHORIZED.equals(responseBody.get(MESSAGE_BODY));
			}
		}

		return false;
	}
}
