package notification.service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import notification.service.Strategy.NotificationStrategy;

@Service
public class PushNotificationService implements NotificationStrategy {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Override
	public void sendNotification(String recipient, String content) {
		messagingTemplate.convertAndSend("topic/notifications", content);

	}

}
