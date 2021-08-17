package br.unisantos.financas.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.unisantos.financas.dto.EmailDTO;

@Service
public class EmailService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;

	public void sendEmail(EmailDTO email) {
		SimpleMailMessage message = prepareSimpleMailMessage(email);
		mailSender.send(message);
	}

	private SimpleMailMessage prepareSimpleMailMessage(EmailDTO email) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email.getTo());
		mailMessage.setFrom(sender);
		mailMessage.setSubject(email.getSubject());
		mailMessage.setSentDate(new Date(System.currentTimeMillis()));
		mailMessage.setText(email.getText());
		return mailMessage;
	}

	private String htmlFromTemplate(EmailDTO email) {
		Context context = new Context();
		context.setVariable("email", email);
		return templateEngine.process("email/email", context);
	}

	public void sendHtmlEmail(EmailDTO email) throws MessagingException {
		MimeMessage message = prepareHtmlEmailMessage(email);
		javaMailSender.send(message);
	}

	private MimeMessage prepareHtmlEmailMessage(EmailDTO email) throws MessagingException {
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo(email.getTo());
		helper.setFrom(sender);
		helper.setSubject(email.getSubject());
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(htmlFromTemplate(email), true);
		return msg;
	}
}
