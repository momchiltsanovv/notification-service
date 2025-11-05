package bg.softuni.notificationservice.web.mapper;

import bg.softuni.notificationservice.model.Notification;
import bg.softuni.notificationservice.web.dto.NotificationResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {


    public static NotificationResponse from(Notification notification) {
        return NotificationResponse.builder()
                                   .subject(notification.getSubject())
                                   .status(notification.getStatus())
                                   .contactInfo(notification.getContactInfo())
                                   .createdOn(notification.getCreatedOn())
                                   .type(notification.getType())
                                   .build();
    }
}
