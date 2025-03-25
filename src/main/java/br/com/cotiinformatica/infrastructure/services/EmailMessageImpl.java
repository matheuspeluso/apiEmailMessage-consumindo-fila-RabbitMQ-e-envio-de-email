package br.com.cotiinformatica.infrastructure.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import br.com.cotiinformatica.domain.contracts.services.EmailService;

@Service
public class EmailMessageImpl implements EmailService {

	private final JavaMailSender mailSender;

	public EmailMessageImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void enviarEmail(String email, String nome, String assunto, String mensagem) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setTo(email);
			helper.setSubject(assunto);
			helper.setText(gerarTemplateEmail(nome, mensagem), true); // Define como HTML

			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException("Erro ao enviar e-mail", e);
		}
	}

	private String gerarTemplateEmail(String nome, String mensagem) {
		return String.format(
				"""
						    <html>
						        <head>
						            <style>
						                body {
						                    font-family: Arial, sans-serif;
						                    background-color: #f4f4f4;
						                    padding: 20px;
						                    display: flex;
						                    justify-content: center;
						                    align-items: center;
						                    height: 100vh;
						                }
						                .container {
						                    max-width: 600px;
						                    background: white;
						                    padding: 20px;
						                    border-radius: 8px;
						                    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
						                    text-align: center;
						                }
						                .header {
						                    background-color: #007bff;
						                    color: white;
						                    padding: 15px;
						                    border-radius: 8px 8px 0 0;
						                    font-size: 20px;
						                    font-weight: bold;
						                }
						                p {
						                    font-size: 16px;
						                    line-height: 1.5;
						                    color: #555;
						                    margin: 20px 0;
						                }
						                .footer {
						                    font-size: 12px;
						                    color: #777;
						                    margin-top: 20px;
						                }
						                .btn {
						                    display: inline-block;
						                    padding: 10px 20px;
						                    font-size: 16px;
						                    color: white;
						                    background-color: #007bff;
						                    text-decoration: none;
						                    border-radius: 5px;
						                    margin-top: 20px;
						                    transition: background 0.3s;
						                }
						                .btn:hover {
						                    background-color: #0056b3;
						                }
						            </style>
						        </head>
						        <body>
						            <div class="container">
						                <div class="header">Olá, %s!</div>
						                <p>%s</p>
						                <a href="https://github.com/matheuspeluso" class="btn" target="_blank">Acesse meu portfólio profissional</a>
						                <p class="footer">Este é um e-mail automático, por favor, não responda.</p>
						            </div>
						        </body>
						    </html>
						""",
				nome, mensagem);
	}

}
