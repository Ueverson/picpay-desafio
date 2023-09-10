package com.br.desafiopicpay.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.br.desafiopicpay.domain.user.User;
import com.br.desafiopicpay.exceptions.NotificationServiceUnavailableException;

@Service
public class NotificationService {

	private JavaMailSender javaMailSender;

	public NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendNotification(User user, String message) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setTo(user.getEmail());
			simpleMailMessage.setText(message);
			javaMailSender.send(simpleMailMessage);

		} catch (Exception e) {
			throw new NotificationServiceUnavailableException("Serviço de notifcação fora do ar");
		}

	}

}
