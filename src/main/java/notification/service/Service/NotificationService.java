package notification.service.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import notification.service.Entity.Notification;
import notification.service.Entity.Status;
import notification.service.Entity.Type;
import notification.service.Repository.NotificationRepository;
import notification.service.Strategy.NotificationContext;

@Service
public class NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private NotificationContext context;

	public Notification createNotification(String recipient, Type type, String content) {
		Notification notification = new Notification();
		notification.setRecipient(recipient);
		notification.setType(type);
		notification.setContent(content);
		notification.setStatus(Status.PENDING);

		// Save notification details with status: PENDING
		Notification savedNotification = notificationRepository.save(notification);

		try {
			// use context to get service(value) based on type(key), then call method form
			// that service
			context.getStrategy(type).sendNotification(recipient, content);

			// if send notification successfully, update status to SENT in savedNotification
			// obj

			savedNotification.setStatus(Status.SENT);
		} catch (Exception e) {
			savedNotification.setStatus(Status.FAILED);
		}

		// update status to db either FAILED or SENT
		return notificationRepository.save(savedNotification);
	}

	// Way 1 - using Optional
	public Notification updateStatus(Long id, Status newStatus) {
		Optional<Notification> notificationOpt = notificationRepository.findById(id);

		if (notificationOpt.isPresent()) {
			// map notificationOpt to notification obj
			Notification notification = notificationOpt.get();
			notification.setStatus(newStatus);
			return notificationRepository.save(notification);
		} else {
			throw new RuntimeException("Notification not found");
		}
	}

	// Way 2 - using Map
	public Notification updateStatusUsingMap(Long id, Status newStatus) {
		// using map to update
		return notificationRepository.findById(id).map(notification -> {
			notification.setStatus(newStatus);
			return notificationRepository.save(notification);
		}).orElseThrow(() -> new RuntimeException("Notification not found"));

	}
}
