package br.com.cotiinformatica.domain.models.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class Email {

	private UUID id;
	private String assunto;
	private String mensagem;


}
