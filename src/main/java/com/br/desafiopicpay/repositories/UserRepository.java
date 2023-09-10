package com.br.desafiopicpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.desafiopicpay.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
