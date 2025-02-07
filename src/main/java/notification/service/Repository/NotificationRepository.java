package notification.service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import notification.service.Entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
}
