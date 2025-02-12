package br.com.cotiinformatica.domain.models.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class DadosMensagem {
	
	private UUID id;
	private String email;
	private String nome;
	private String assunto;
	private String mensagem;
}
