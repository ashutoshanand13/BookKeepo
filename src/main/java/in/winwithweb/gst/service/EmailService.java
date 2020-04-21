/**
 * 
 */
package in.winwithweb.gst.service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import in.winwithweb.gst.model.User;

/**
 * @author sachingoyal
 *
 */
@Service("emailService")
@Component
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * This method will send compose and send the message
	 */
	public void sendEmail(User user, String emailFrom, String subject, String body) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setFrom(new InternetAddress(emailFrom, "BookKeepo"));
			message.setSubject(subject);
			message.setContent(body.replace("$tokenValue", user.getToken()).replace("$email", user.getEmail()),
					"text/html");
			mailSender.send(message);
		} catch (Exception e) {

		}

	}

	/**
	 * This method will send compose and send the message
	 */
	public void forgetPassword(User user, String emailFrom, String subject, String body, String password) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setFrom(new InternetAddress(emailFrom, "BookKeepo"));
			message.setSubject(subject);
			message.setContent(body.replace("$firstName", user.getName()).replace("$Password", password), "text/html");
			mailSender.send(message);
		} catch (Exception e) {

		}

	}

}
