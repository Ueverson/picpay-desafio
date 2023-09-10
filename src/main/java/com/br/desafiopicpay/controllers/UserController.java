package com.br.desafiopicpay.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.desafiopicpay.domain.user.User;
import com.br.desafiopicpay.dtos.UserDTO;
import com.br.desafiopicpay.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService service;
	
	public UserController(UserService userService) {
		this.service = userService;
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
		User newUser = this.service.createUser(userDTO);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = this.service.getAllUsersUser();
		return new ResponseEntity<>(users, HttpStatus.CREATED);
	}
}
