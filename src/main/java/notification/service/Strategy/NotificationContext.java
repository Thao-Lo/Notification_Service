package notification.service.Strategy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import notification.service.Entity.Type;
import notification.service.Service.EmailNotificationService;
import notification.service.Service.PushNotificationService;
import notification.service.Service.SMSNotificationService;

@Component
public class NotificationContext {
	private final Map<Type, NotificationStrategy> strategies = new HashMap<>();
	public NotificationContext(
			EmailNotificationService emailService,
			SMSNotificationService smsService,
			PushNotificationService pushService) {
		
		strategies.put(Type.EMAIL, emailService);
		strategies.put(Type.SMS, smsService);
		strategies.put(Type.PUSH_NOTIFICATION, pushService);
	}
	public NotificationStrategy getStrategy(Type type) {
		return strategies.get(type);
	}
}
