package com.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import com.bean.SeminarRegistrationMailBean;
import com.bean.UserBean;

@Service
public class EmailService {
	
	
	public void sendRegisterationMail(UserBean user,SeminarRegistrationMailBean mailMessage) {
		String to = user.getEmailID(); // to address
		final String from = "testjap517@gmail.com";// from address
		final String appPassword = "afxfovyxdkfamsnz";
		Properties prop = System.getProperties();

		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.ssl.enable", "false");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(from, appPassword);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(from);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(mailMessage.getSubject());
			message.setContent(mailMessage.getBody().replaceAll("\n", "<br>"),"text/html");
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("something went wrong...........");
		}

	}
}

