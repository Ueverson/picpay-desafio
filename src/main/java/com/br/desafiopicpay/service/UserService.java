package com.br.desafiopicpay.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.desafiopicpay.domain.user.User;
import com.br.desafiopicpay.domain.user.UserType;
import com.br.desafiopicpay.dtos.UserDTO;
import com.br.desafiopicpay.exceptions.InsufficientBalanceException;
import com.br.desafiopicpay.exceptions.UnauthorizedTransactionException;
import com.br.desafiopicpay.exceptions.UserNotFoundException;
import com.br.desafiopicpay.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public void validateTransaction(User sender, BigDecimal amount) {

		if (UserType.MERCHANT.equals(sender.getUsertype())) {
			throw new UnauthorizedTransactionException("User MERCHANT is not authorized to make transaction");
		}
		if (sender.getBalance().compareTo(amount) < 0) {
			throw new InsufficientBalanceException("user with insufficient balance");
		}
	}

	public User findUserById(Long id) {
		return this.repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
	}

	public User createUser(UserDTO userDTO) {
		User newUser = new User(userDTO);
		saveUser(Arrays.asList(newUser));
	
		return newUser;
	}
	
	public List<User> getAllUsersUser() {
		return this.repository.findAll();
	}
	
	public void updateBalance(User sender, User receiver, BigDecimal value) {
		sender.setBalance(sender.getBalance().subtract(value));
		receiver.setBalance(receiver.getBalance().add(value));
		saveUser(Arrays.asList(sender, receiver));
	}
	
	public void saveUser(List<User> users) {
		users.forEach(user -> this.repository.save(user));
	}

}
