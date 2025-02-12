package br.com.cotiinformatica.domain.contracts.services;

public interface EmailService {
	
	void enviarEmail(String email, String nome, String assunto, String mensagem);
	
}
