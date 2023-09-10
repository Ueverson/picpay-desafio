package com.br.desafiopicpay.domain.user;

import java.math.BigDecimal;

import com.br.desafiopicpay.dtos.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String cpf;

	@Column(unique = true)
	private String email;

	private String password;

	private BigDecimal balance;

	@Enumerated(EnumType.STRING)
	private UserType usertype;

	public User(UserDTO data) {
		this.name = data.name();
		this.cpf = data.cpf();
		this.email = data.email();
		this.password = data.password();
		this.balance = data.balance();
		this.usertype = data.userType();
	}
}
