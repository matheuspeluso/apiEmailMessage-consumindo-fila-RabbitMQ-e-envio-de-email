package br.com.cotiinformatica.infrastructure.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.contracts.services.EmailService;

@Service
public class EmailMessageImpl implements EmailService{
	
	private JavaMailSender mailSender;
	
	public EmailMessageImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void enviarEmail(String email, String nome, String assunto, String mensagem) {
		
		SimpleMailMessage mensagemEmail = new SimpleMailMessage();
		
		mensagemEmail.setTo(email);
		mensagemEmail.setSubject(assunto);
		mensagemEmail.setText(mensagem);
		mailSender.send(mensagemEmail);
	}

}
