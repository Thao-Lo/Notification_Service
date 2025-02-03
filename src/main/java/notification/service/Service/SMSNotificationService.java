package notification.service.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;
import notification.service.Config.TwilioConfig;
import notification.service.Strategy.NotificationStrategy;


@Service
public class SMSNotificationService implements NotificationStrategy {
	
	@Autowired
	private TwilioConfig config;
	
	private static final Logger logger = LoggerFactory.getLogger(SMSNotificationService.class);
	
	@PostConstruct
	public void initTwilio() {		
		Twilio.init(config.getAccountSid(), config.getAuthToken());
	}
	
	
	@Override
	public void sendNotification(String recipient, String content) {
		try {
			if(!recipient.startsWith("+")) {
				recipient = "+" + recipient.trim();
			}
			Message.creator(new PhoneNumber(recipient),new PhoneNumber(config.getFromPhone()),content).create();
			logger.info("SMS send to: {}", recipient);
		}catch (Exception e) {
			logger.error("Failed to send SMS to: {}: {}", recipient, e.getMessage());
		}
			
	}

}
