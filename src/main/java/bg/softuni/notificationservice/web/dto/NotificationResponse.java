package bg.softuni.notificationservice.web.dto;

import bg.softuni.notificationservice.model.NotificationStatus;
import bg.softuni.notificationservice.model.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {

    private String subject;

    private LocalDateTime createdOn;

    private String contactInfo;

    private NotificationStatus status;

    private NotificationType type;


}
