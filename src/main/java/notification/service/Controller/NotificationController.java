package notification.service.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import notification.service.Entity.Notification;
import notification.service.Entity.Status;
import notification.service.Entity.Type;
import notification.service.Service.NotificationService;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PostMapping("/send")
	public ResponseEntity<Object> sendNotidication(@RequestParam String recipient, @RequestParam String content,
			@RequestParam Type type) {
		try {
			Notification notification = notificationService.createNotification(recipient, type, content);
			
			notificationService.updateStatus(notification.getId(), Status.SENT);
			return new ResponseEntity<>(Map.of("message", "Sending Notification."), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
}
