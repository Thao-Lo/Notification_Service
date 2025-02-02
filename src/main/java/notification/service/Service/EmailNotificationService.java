package notification.service.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import notification.service.Strategy.NotificationStrategy;

@Service
public class EmailNotificationService implements NotificationStrategy {

	@Autowired
	private JavaMailSender mailSender;

	private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);

	@Override
	public void sendNotification(String recipient, String content) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(recipient);
		message.setSubject("Notification from Z");
		message.setText(content);
		mailSender.send(message);
		logger.info("Email send to: {}", recipient);
	}

}
