package src;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailGenerator {

	public static boolean sendEmail(String emailTO, String user, String subject, String messageContent) {

		boolean result = false;

		String from = "Electioncreate123@gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		String emailBody = "<html><body style=\"font-family:Palatino Linotype;font-size:14;\">" 
				+ "<p>Hi " + user + ",</p>"
				+ "<p>"+messageContent+"</p>"
				+ "<p style=\"color:#005580;\">Regards,<br/>Election Team<br/></p>"
				+ "</body></html>";

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "createElection321@");
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTO));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(from));

			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			// messageBodyPart.setText(SurveyConstants.EMAIL_BODY);
			messageBodyPart.setContent(emailBody, "text/html; charset=utf-8");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			result = true;

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			result = false;
		}

		return result;
	}
}
