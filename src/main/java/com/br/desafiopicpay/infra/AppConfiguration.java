package com.br.desafiopicpay.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

	@Bean
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}
}
