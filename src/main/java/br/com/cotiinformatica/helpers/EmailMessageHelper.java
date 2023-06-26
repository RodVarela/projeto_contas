package br.com.cotiinformatica.helpers;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import br.com.cotiinformatica.dtos.EmailMessageDto;

public class EmailMessageHelper {

	// parâmetros da conta utilizada para fazer o envio dos emails
	private static final String ACCOUNT = "cotiaulajava@outlook.com";
	private static final String PASSWORD = "@Admin123456";
	private static final String SMTP = "smtp-mail.outlook.com";
	private static final Integer PORT = 587;

	// método para realizar o envio do email
	public static void send(final EmailMessageDto dto) throws Exception {

		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setUsername(ACCOUNT);
		senderImpl.setPassword(PASSWORD);
		senderImpl.setHost(SMTP);
		senderImpl.setPort(PORT);
		
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.transport.protocol", "smtp");
		senderImpl.setJavaMailProperties(properties);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
				messageHelper.setFrom(ACCOUNT);
				messageHelper.setTo(dto.getEmailDestinatario());
				messageHelper.setSubject(dto.getAssunto());
				messageHelper.setText(dto.getConteudoMensagem());
			}
		};
		
		senderImpl.send(messagePreparator);
	}
}



