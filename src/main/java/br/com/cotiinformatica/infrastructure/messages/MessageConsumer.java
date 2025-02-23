package br.com.cotiinformatica.infrastructure.messages;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.domain.contracts.services.EmailService;
import br.com.cotiinformatica.domain.models.entities.DadosMensagem;

@Component
public class MessageConsumer {

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	EmailService emailService;

	@RabbitListener(queues = "mensagem_email_cliente")
	public void receive(@Payload String payload) {

		try {
			var filaMensageria = objectMapper.readValue(payload, DadosMensagem.class);

			// Enviar email para o cliente
			emailService.enviarEmail(filaMensageria.getEmail(), filaMensageria.getNome(), filaMensageria.getAssunto(),
					filaMensageria.getMensagem());

			System.out.println("Email enviado com sucesso! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
