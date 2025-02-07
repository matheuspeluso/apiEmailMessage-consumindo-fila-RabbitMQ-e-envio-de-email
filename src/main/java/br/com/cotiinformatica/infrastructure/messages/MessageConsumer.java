package br.com.cotiinformatica.infrastructure.messages;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
	
	@RabbitListener(queues = "mensagem_email_cliente")
	public void receive(@Payload String payload) {
		
		try {
			System.out.println("Mensagem recebida: " + payload);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
