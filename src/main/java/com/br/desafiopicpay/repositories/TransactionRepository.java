package com.br.desafiopicpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.desafiopicpay.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
