package notification.service.Strategy;

public interface NotificationStrategy {
	void sendNotification(String recipient, String content);
}
