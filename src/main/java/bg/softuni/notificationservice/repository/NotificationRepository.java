package bg.softuni.notificationservice.repository;

import bg.softuni.notificationservice.model.Notification;
import bg.softuni.notificationservice.model.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findAllByUserId(UUID userId);
}