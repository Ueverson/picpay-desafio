package com.br.desafiopicpay.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafiopicpay.domain.transaction.Transaction;
import com.br.desafiopicpay.dtos.TransactionDTO;
import com.br.desafiopicpay.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	private TransactionService service;
	
	public TransactionController(TransactionService transactionService) {
		this.service = transactionService;
	}

	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO){
		Transaction newTransaction = this.service.createTransaction(transactionDTO);
		return new ResponseEntity<>(newTransaction, HttpStatus.OK);
	}
}
