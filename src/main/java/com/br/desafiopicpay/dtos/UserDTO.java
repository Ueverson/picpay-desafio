package com.br.desafiopicpay.dtos;

import java.math.BigDecimal;

import com.br.desafiopicpay.domain.user.UserType;

public record UserDTO(String name, String cpf, BigDecimal balance, String email, String password, UserType userType) {

}
